package com.axel.pruebatecnica.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.axel.pruebatecnica.api.service.implementations.ConcertService;
import com.axel.pruebatecnica.api.service.implementations.ConferenceService;
import com.axel.pruebatecnica.api.service.implementations.EventService;
import com.axel.pruebatecnica.api.service.implementations.TheaterService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/event")
public class EventsController {

	private final EventService eventService;
	
	private final ConcertService concertService;
	private final ConferenceService conferenceService;
	private final TheaterService theaterService;
	
//	//vista crear
//	@GetMapping("/createEvent")
//	ModelAndView createView() {
//		ModelAndView mav = new ModelAndView("event/createEvent");
//		return mav;
//	}
//
//	//crear evento
//    @PostMapping("/createEvent")
//    ModelAndView createEvent(@ModelAttribute EventCreateDTO dto) {
//
//        EventEntity event = EventMapper.fromDTO(dto);
//        eventService.create(event);
//
//        ModelAndView mav = new ModelAndView("event/allEvents");
//        mav.addObject("events", eventService.events());
//        return mav;
//    }

    //mostrar
    @GetMapping("/allEvents")
	ModelAndView allEvents() {
		ModelAndView mav = new ModelAndView("event/allEvents");
		mav.addObject("concerts", concertService.allConcerts());
		mav.addObject("conferences", conferenceService.allConferences());
		mav.addObject("theaters", theaterService.allTheaters());
		return mav;
	}
    
  //mostrar
//  @GetMapping("/allEvents")
//	ModelAndView allEvents() {
//		ModelAndView mav = new ModelAndView("event/allEvents");
//		mav.addObject("events", eventService.allEvents());
//		return mav;
//	}
    
//    //eliminar (solo admin)
//    @PostMapping("/deleteEvent/{idEvent}")
//    ModelAndView deleteEvent(@PathVariable int idEvent) {
//    	
//        try {
//        	eventService.delete(idEvent);
//        	return allEvents();
//		} catch (Exception e) {
//			ModelAndView error = new ModelAndView("error/error");
//			error.addObject("tituloError", "Hubo un problema");
//			error.addObject("mensajeError", "No se puede eliminar el evento porque hay reservas activas");
//			return error;
//		}
//        
//    }
    
}
