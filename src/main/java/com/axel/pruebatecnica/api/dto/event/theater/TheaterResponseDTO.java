package com.axel.pruebatecnica.api.dto.event.theater;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TheaterResponseDTO {

    private int idEvent;
    private String name;
    private LocalDateTime dateTime;
    private int generalCant;
    private int vipCant;

}
