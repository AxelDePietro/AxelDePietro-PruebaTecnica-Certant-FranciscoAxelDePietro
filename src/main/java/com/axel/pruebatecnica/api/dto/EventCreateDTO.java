package com.axel.pruebatecnica.api.dto;

import lombok.Data;

@Data
public class EventCreateDTO {

    private String name;
    private String dateTime;
    private String eventType;

    
    //asientos
    private Integer theaterGeneral;
    private Integer theaterVip;

    private Integer concertPlatea;
    private Integer concertCampo;
    private Integer concertPalco;

    private Integer conferenceSeatsMyG;
    private Integer conferenceSeatsNoMyG;
}