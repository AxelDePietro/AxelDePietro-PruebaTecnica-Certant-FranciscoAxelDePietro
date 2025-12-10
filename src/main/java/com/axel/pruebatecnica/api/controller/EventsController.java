package com.axel.pruebatecnica.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/event")
public class EventsController {

	@GetMapping("/createEventGeneral")
	ModelAndView createEventGeneral() {
		return new ModelAndView("event/createEventGeneral");
	}
	
}
