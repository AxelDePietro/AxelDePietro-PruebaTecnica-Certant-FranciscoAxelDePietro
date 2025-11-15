package com.axel.pruebatecnica.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.axel.pruebatecnica.api.entity.envents.EventConferenceEntity;
import com.axel.pruebatecnica.api.service.implementations.ConferenceService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/conference")
public class ConferenceController {

	private final ConferenceService conferenceService;
	
	@GetMapping("/createConferenceView")
	public ModelAndView createConferenceView() {
		return new ModelAndView("conference/createConference");
	}

	@GetMapping("/allConferences")
	public ModelAndView allConferences() {
		ModelAndView mav = new ModelAndView("conference/allConferences");
		mav.addObject("conferences", conferenceService.allConferences());
		return mav;

	}

	@PostMapping("/createConference")
	public ModelAndView createConference(@ModelAttribute EventConferenceEntity conferenceEntity ) {

		conferenceService.createConference( conferenceEntity);

		return allConferences();

	}

	@PostMapping("/deleteConference/{idConference}")
	public ModelAndView deleteConference(@PathVariable int idConference) {

		conferenceService.delete(idConference);

		return allConferences();

	}
	
}
