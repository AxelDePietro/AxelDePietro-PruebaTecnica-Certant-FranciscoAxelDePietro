package com.axel.pruebatecnica.api.controller;

import com.axel.pruebatecnica.api.dto.EventCreateDTO;
import com.axel.pruebatecnica.api.mapper.EventMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.axel.pruebatecnica.api.entity.EventEntity;
import com.axel.pruebatecnica.api.service.implementations.EventService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/event")
public class EventController {

	private final EventService eventService;
	
	//vista crear
	@GetMapping("/createEvent")
	ModelAndView createView() {
		ModelAndView mav = new ModelAndView("event/createEvent");
		return mav;
	}

	//crear evento
    @PostMapping("/createEvent")
    ModelAndView createEvent(@ModelAttribute EventCreateDTO dto) {

        EventEntity event = EventMapper.fromDTO(dto);
        eventService.create(event);

        ModelAndView mav = new ModelAndView("event/allEvents");
        mav.addObject("events", eventService.events());
        return mav;
    }

    //mostrar
    @GetMapping("/allEvents")
	ModelAndView allEvents() {
		ModelAndView mav = new ModelAndView("event/allEvents");
		mav.addObject("events", eventService.events());
		return mav;
	}
    
    //eliminar (solo admin)
    @PostMapping("/deleteEvent/{idEvent}")
    ModelAndView deleteEvent(@PathVariable int idEvent) {
    	
        try {
        	eventService.delete(idEvent);
        	return allEvents();
		} catch (Exception e) {
			ModelAndView error = new ModelAndView("error/error");
			error.addObject("tituloError", "Hubo un problema");
			error.addObject("mensajeError", "No se puede eliminar el evento porque hay reservas activas");
			return error;
		}
        
    }
    
}
