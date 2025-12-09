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
public class EventConferenceEntity extends EventEntity {

	private int charlaConMeetCant;
	private int charlaSinMeetCant;

	@Transient
	public int usedSeats() {

		return getBookings().size();
	}

	@Transient
	public int totalRermainigCapacity() {
		return charlaConMeetCant + charlaSinMeetCant;
	}

	@Transient
	public int totalCapacity() {
		return totalRermainigCapacity() + usedSeats();
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString() + "( con meet and greed = " + getCharlaConMeetCant() + ", sin meet and greed = " + getCharlaSinMeetCant() + " ) asientos ( usados = " +  usedSeats() + ", total = " + totalCapacity() + ", restantes = " + totalRermainigCapacity() + " )";
	}
	
}
