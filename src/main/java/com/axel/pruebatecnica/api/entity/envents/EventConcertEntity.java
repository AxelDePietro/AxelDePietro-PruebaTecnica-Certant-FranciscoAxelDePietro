package com.axel.pruebatecnica.api.entity.envents;

import jakarta.persistence.Entity;
import jakarta.persistence.Transient;
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
	
	private int campoCant;
	private int palcoCant;
	private int plateaCant;
	
	@Transient
	private int usedSeats () {
		return super.getBookings().size();
	}
	
	@Transient
	private int totalRermainigCapacity (){
		return campoCant + palcoCant + plateaCant;
	}
	
	@Transient
	private int totalCapacity () {
		return totalRermainigCapacity() + usedSeats();
	}
	
	
	
}
