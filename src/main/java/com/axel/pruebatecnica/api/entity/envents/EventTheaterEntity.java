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
public class EventTheaterEntity extends EventEntity{

	private int generalCant;
	private int vipCant;
	
	@Transient
	public int usedSeats () {
		
		if(!getBookings().isEmpty()) {
			return getBookings().size();
		}
		return 0;
	}
	
	@Transient
	public int totalRermainigCapacity (){
		return generalCant + vipCant;
	}
	
	@Transient
	public int totalCapacity () {
		return totalRermainigCapacity() + usedSeats();
	}
}
