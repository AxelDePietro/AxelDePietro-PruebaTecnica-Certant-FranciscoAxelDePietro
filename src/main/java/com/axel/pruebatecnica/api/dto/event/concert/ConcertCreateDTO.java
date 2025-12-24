package com.axel.pruebatecnica.api.dto.event.concert;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public class ConcertCreateDTO {

    @NotBlank(message = "el evento no puede tener el nombre vacio")
    private String name;
    @NotBlank(message = "el evento no puede tener la fecha vacia")
    private LocalDateTime dateTime;
    private int campoCant;
    private int palcoCant;
    private int plateaCant;

}
