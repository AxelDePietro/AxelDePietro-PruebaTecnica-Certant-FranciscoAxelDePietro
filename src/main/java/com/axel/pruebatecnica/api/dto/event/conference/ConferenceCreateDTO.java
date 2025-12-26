package com.axel.pruebatecnica.api.dto.event.conference;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ConferenceCreateDTO {

    @NotBlank(message = "el evento no puede tener el nombre vacio")
    private String name;
    @NotBlank(message = "el evento no puede tener la fecha vacia")
    private LocalDateTime dateTime;
    private int charlaConMeetCant;
    private int charlaSinMeetCant;

}
