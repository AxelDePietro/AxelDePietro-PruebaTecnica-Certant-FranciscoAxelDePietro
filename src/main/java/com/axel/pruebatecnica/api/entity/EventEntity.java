package com.axel.pruebatecnica.api.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.axel.pruebatecnica.api.entity.enums.EventTypeEnum;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import lombok.Data;

@Entity
@Data
public class EventEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_event")
	private int idEvent;

	@Column(name = "event_name")
	private String name;

	@Column(name = "event_date_time")
	private LocalDateTime dateTime;

	// tipo de evento
	@Column(name = "event_type")
	@Enumerated(EnumType.STRING)
	private EventTypeEnum eventTypeEnum;

	// realaciones

	@OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<SeatCapacityEntity> seatCapacity = new ArrayList<>();

	@OneToMany(mappedBy = "event", cascade = { CascadeType.PERSIST, CascadeType.MERGE }, orphanRemoval = false)
	private List<BookingEntity> bookings = new ArrayList<>();

	

	//
	@Transient
	public int getUsedSeats() {
		return bookings.size();
	}

	@Transient
	public int getRemainingSeats() {
		return seatCapacity.stream()
				.mapToInt(SeatCapacityEntity::getCapacity)
				.sum();
	}

	@Transient
	public int getTotalCapacity() {
		return getUsedSeats() + getRemainingSeats();
	}

	@Transient
	public int getAvailableSeats() {
		return getRemainingSeats();
	}

	@Transient
	public int getSeatAvailable() {
		return getAvailableSeats();
	}

	@Transient
	public List<SeatCapacityEntity> getAvailableCapacities() {
		return seatCapacity.stream()
				.filter(s -> s.getCapacity() > 0)
				.toList();
	}

}
