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

    //mostrar
    @GetMapping("/allEvents")
	ModelAndView allEvents() {
		ModelAndView mav = new ModelAndView("event/allEvents");
		mav.addObject("concerts", concertService.allConcerts());
		mav.addObject("conferences", conferenceService.allConferences());
		mav.addObject("theaters", theaterService.allTheaters());
		return mav;
	}
    
}
