package com.axel.pruebatecnica.api.exceptions;

@SuppressWarnings("serial")
public class SinReservas extends RuntimeException {

	public SinReservas() {
		super("el listado de reservas esta vacio");
	}
	
}
