package com.axel.pruebatecnica.api.controller.newcontrollers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.axel.pruebatecnica.api.entity.envents.EventTheaterEntity;
import com.axel.pruebatecnica.api.service.implementations.TheaterService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/theater")
public class TheaterController {

	private final TheaterService theaterService;

	@GetMapping("/createTheaterView")
	public ModelAndView createTheaterView() {
		return new ModelAndView("concert/createTheater");
	}

	@GetMapping("/allTheaters")
	public ModelAndView allTheaters() {
		ModelAndView mav = new ModelAndView("concert/allTheater");
		mav.addObject("concerts", theaterService.allTheaters());
		return mav;

	}

	@PostMapping("/createTheater")
	public ModelAndView createTheater(@ModelAttribute EventTheaterEntity theaterEntity) {

		theaterService.createTheater(theaterEntity);

		return allTheaters();

	}

	@PostMapping("/deleteConcert/{idConcert}")
	public ModelAndView deleteConcert(@PathVariable int idConcert) {

		theaterService.delete(idConcert);

		return allTheaters();

	}

}
