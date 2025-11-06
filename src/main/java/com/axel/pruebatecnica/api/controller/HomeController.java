package com.axel.pruebatecnica.api.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/api/home")
public class HomeController {

	//ruta a home o html principal
	@GetMapping
	public ModelAndView homeView(@AuthenticationPrincipal User user) {
		ModelAndView mav = new ModelAndView("home/home");
		return mav;
	}

}
