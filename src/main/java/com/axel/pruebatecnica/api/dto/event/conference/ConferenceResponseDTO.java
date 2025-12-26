package com.axel.pruebatecnica.api.dto.event.conference;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ConferenceResponseDTO {

    private int idEvent;
    private String name;
    private LocalDateTime dateTime;
    private int charlaConMeetCant;
    private int charlaSinMeetCant;

}
