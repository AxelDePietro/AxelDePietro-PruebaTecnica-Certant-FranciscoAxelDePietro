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
public class EventTheaterEntity extends EventEntity{

	private int totalCapacity;
	
	private int generalCant;
	private int vipCant;
	
}
