package com.axel.pruebatecnica.api.dto.event;

import com.axel.pruebatecnica.api.entity.BookingEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class EventResponseDTO {

    private int idEvent;

    private String name;

    private LocalDateTime dateTime;

}
