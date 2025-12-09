package com.axel.pruebatecnica.api.entity.envents;

import jakarta.persistence.Entity;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class EventConcertEntity extends EventEntity {

	private int campoCant;
	private int palcoCant;
	private int plateaCant;

	@Transient
	public int usedSeats() {

		return getBookings().size();
	}

	@Transient
	public int totalRermainigCapacity() {
		return campoCant + palcoCant + plateaCant;
	}

	@Transient
	public int totalCapacity() {
		return totalRermainigCapacity() + usedSeats();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString() + " ( campo = " +  getCampoCant() + ", palco = " + getPalcoCant() + ", platea = " + getPlateaCant() + " ) asientos ( usados = " +  usedSeats() + ", total = " + totalCapacity() + ", restantes = " + totalRermainigCapacity() + " )";
	}
	
}
