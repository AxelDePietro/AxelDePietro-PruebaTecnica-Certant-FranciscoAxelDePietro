package com.axel.pruebatecnica.api.dto.event.theater;

import com.axel.pruebatecnica.api.dto.event.EventCreateDTO;
import com.axel.pruebatecnica.api.dto.event.EventResponseDTO;

public class TheaterCreateDTO extends EventCreateDTO{

    private int generalCant;
    private int vipCant;

}
