package com.axel.pruebatecnica.api.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

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

    @ExceptionHandler(NoEncontrado.class)
    public ResponseEntity<Error> manejarNoEncontrado(NoEncontrado ex, HttpServletRequest request) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new Error(
                        500,
                        "NO_ENCONTRADO",
                        ex.getMessage(),
                        request.getRequestURI()));

    }

}
