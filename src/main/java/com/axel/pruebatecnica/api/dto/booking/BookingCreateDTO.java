package com.axel.pruebatecnica.api.dto.booking;

import com.axel.pruebatecnica.api.entity.UserEntity;
import com.axel.pruebatecnica.api.entity.enums.SeatTypeEnum;
import com.axel.pruebatecnica.api.entity.envents.EventEntity;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class BookingCreateDTO {

    private String seatType;

    private int idEvent;

}
