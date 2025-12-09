package com.axel.pruebatecnica.api.exceptions;

public class ListaVacia extends RuntimeException {

	public ListaVacia() {
		super("la lista no tiene contenido");
	}

}
