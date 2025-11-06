package com.axel.pruebatecnica.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.axel.pruebatecnica.api.entity.UserEntity;
import com.axel.pruebatecnica.api.service.implementations.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class AuthController {

	private final UserService userService;

	//vista de registro
	@GetMapping("/register")
	ModelAndView registerView() {

		ModelAndView mav = new ModelAndView("register/register");
		return mav;
	}

	//registro, creacion de usuario
	@PostMapping("/register")
	ModelAndView register(@ModelAttribute UserEntity user) {

		userService.save(user);
		ModelAndView mav = new ModelAndView("home/home");
		return mav;
	}

	//pagin de login
	@GetMapping("/login")
	ModelAndView loginView() {
		
		ModelAndView mav = new ModelAndView("register/login");
		return mav;
	}

}
