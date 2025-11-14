package com.axel.pruebatecnica.api.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.axel.pruebatecnica.api.service.implementations.BookingService;
import com.axel.pruebatecnica.api.service.implementations.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

	private final UserService userService;
	private final BookingService bookingService;

	// user de spring para tomar la sesion actual del contexto de la aplicacion, asi como le @AuthnticationPrincipal
	@GetMapping("/personalInfo")
	ModelAndView personalInfo(@AuthenticationPrincipal User user) {

		ModelAndView mav = new ModelAndView("user/personalInformation");
		mav.addObject("user", userService.findByUsername(user.getUsername()));
		return mav;
	}

	//reservas delusuario activo
//	@GetMapping("/misReservas")
//	ModelAndView myBookings(@AuthenticationPrincipal User user) {
//
//		UserEntity userAux = userService.findByUsername(user.getUsername());
//
//		ModelAndView mav = new ModelAndView("user/misReservas");
//		mav.addObject("mybookings", bookingService.myBookings(userAux.getIdUser()));
//		return mav;
//	}

}
