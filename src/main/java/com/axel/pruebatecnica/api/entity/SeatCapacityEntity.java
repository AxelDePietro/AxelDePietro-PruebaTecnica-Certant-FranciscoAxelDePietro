package com.axel.pruebatecnica.api.entity;

import com.axel.pruebatecnica.api.entity.enums.SeatTypeEnum;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class SeatCapacityEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_seat_capacity")
	private Long idSeatCapacity;
	
	//capaciad de asintos
	private Integer capacity;
	
	//enum tipo de asiento 
	@Enumerated(EnumType.STRING)
	@Column(name = "seat_type")
	private SeatTypeEnum seatType;

	@ManyToOne
	@JoinColumn(name = "event_id")
	private EventEntity event;
}
