package com.axel.pruebatecnica.api.dto.event.concert;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ConcertResponseDTO {

    private int idEvent;
    private String name;
    private LocalDateTime dateTime;    private int campoCant;
    private int palcoCant;
    private int plateaCant;

}
