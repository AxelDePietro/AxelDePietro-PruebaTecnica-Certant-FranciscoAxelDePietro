package com.axel.pruebatecnica.api.dto.booking;

import com.axel.pruebatecnica.api.entity.UserEntity;
import com.axel.pruebatecnica.api.entity.enums.SeatTypeEnum;
import com.axel.pruebatecnica.api.entity.envents.EventEntity;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class BookingResponseDTO {

    private int idBooking;

    private int price;

    private String seatType;

    private int idUser;

    private int idEvent;

}
