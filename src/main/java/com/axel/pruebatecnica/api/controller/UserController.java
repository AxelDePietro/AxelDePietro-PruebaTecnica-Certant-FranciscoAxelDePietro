package com.axel.pruebatecnica.api.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.axel.pruebatecnica.api.service.implementations.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

	private final UserService userService;
	
	// user de spring para tomar la sesion actual del contexto de la aplicacion, asi como le @AuthnticationPrincipal
	@GetMapping("/personalInfo")
	ModelAndView personalInfo(@AuthenticationPrincipal User user) {

		ModelAndView mav = new ModelAndView("user/personalInformation");
		mav.addObject("user", userService.findByUsername(user.getUsername()));
		return mav;
	}

}
