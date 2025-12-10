package com.axel.pruebatecnica.api.exceptions;

@SuppressWarnings("serial")
public class ListaVacia extends RuntimeException {

	public ListaVacia() {
		super("la lista no tiene contenido");
	}

}
