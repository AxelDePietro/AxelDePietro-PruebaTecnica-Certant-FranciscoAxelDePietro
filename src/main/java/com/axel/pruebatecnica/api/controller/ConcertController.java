package com.axel.pruebatecnica.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.axel.pruebatecnica.api.entity.envents.EventConcertEntity;
import com.axel.pruebatecnica.api.service.implementations.ConcertService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/concert")
public class ConcertController {

	private final ConcertService concertService;
	
	@GetMapping("/createConcertView")
	public ModelAndView createConcertView () {
		return new ModelAndView("concert/createConcert");
	}
	
	@GetMapping("/allConcerts")
	public ModelAndView allConcerts () {
		ModelAndView mav = new ModelAndView("concert/allConcerts");
		mav.addObject("concerts", concertService.allConcerts());
		return mav;
	
	}
	
	@PostMapping("/createConcert")
	public ModelAndView createConcert (@ModelAttribute EventConcertEntity concertEntity) {
		
		concertService.createConcert(concertEntity);
		
		return allConcerts();
		
	}
	
	@PostMapping("/deleteConcert/{idConcert}")
	public ModelAndView deleteConcert (@PathVariable int idConcert) {
		
		concertService.delete(idConcert);
		
		return allConcerts();
		
	}
	
}
