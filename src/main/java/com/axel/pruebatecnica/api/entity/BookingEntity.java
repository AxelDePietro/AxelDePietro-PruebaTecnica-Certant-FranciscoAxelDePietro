package com.axel.pruebatecnica.api.entity;

import com.axel.pruebatecnica.api.entity.enums.SeatTypeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookingEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_booking")
	private int idBooking;

    @Column(name = "booking_price")
	private int price;

    //toma el valor del enum de seatCapacity
    @Enumerated(EnumType.STRING)
    private SeatTypeEnum seatType;

	@ManyToOne
	@JoinColumn(name = "fk_event")
	private EventEntity event;

	@ManyToOne
	@JoinColumn(name = "fk_user")
	private UserEntity user;
}
