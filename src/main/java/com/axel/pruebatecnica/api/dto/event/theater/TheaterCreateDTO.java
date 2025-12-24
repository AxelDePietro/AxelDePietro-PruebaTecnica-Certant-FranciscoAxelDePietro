package com.axel.pruebatecnica.api.dto.event.theater;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public class TheaterCreateDTO {

    @NotBlank(message = "el evento no puede tener el nombre vacio")
    private String name;
    @NotBlank(message = "el evento no puede tener la fecha vacia")
    private LocalDateTime dateTime;
    private int generalCant;
    private int vipCant;

}
