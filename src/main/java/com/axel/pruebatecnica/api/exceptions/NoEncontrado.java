package com.axel.pruebatecnica.api.exceptions;

public class NoEncontrado extends RuntimeException {
    public NoEncontrado(int idTheater) {
        super("La entidad con id " + idTheater + " no se encontro");
    }
}
