package com.axel.pruebatecnica.api.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ListaVacia.class)
	public ModelAndView manejearListaVacia(ListaVacia ex) {
		ModelAndView mav = new ModelAndView("error/listaVacia");
		mav.addObject("mensaje", ex.getMessage());
		return mav;
	}
	
	@ExceptionHandler(SinReservas.class)
	public ModelAndView manejearSinReservas(SinReservas ex) {
		ModelAndView mav = new ModelAndView("error/sinReservas");
		mav.addObject("mensaje", ex.getMessage());
		return mav;
	}

}
