package com.axel.pruebatecnica.api.entity.envents;

import jakarta.persistence.Entity;
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
public class EventConcertEntity extends EventEntity{
	
	private int totalCapacity;
	
	private int campoCant;
	private int palcoCant;
	private int plateaCant;
	

}
