package com.axel.pruebatecnica.api.entity.envents;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.axel.pruebatecnica.api.entity.BookingEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class EventEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idEvento;
	
	private int nombre;
	
	private LocalDateTime dateTime;
	
	@OneToMany(mappedBy = "event_entity", cascade = CascadeType.ALL)
	private List<BookingEntity> bookings = new ArrayList<>();
}
