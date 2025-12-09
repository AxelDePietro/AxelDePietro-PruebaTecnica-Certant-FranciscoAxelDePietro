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
public class EventTheaterEntity extends EventEntity {

	private int generalCant;
	private int vipCant;

	@Transient
	public int usedSeats() {

		return getBookings().size();
	}

	@Transient
	public int totalRermainigCapacity() {
		return generalCant + vipCant;
	}

	@Transient
	public int totalCapacity() {
		return totalRermainigCapacity() + usedSeats();
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString() + "( general = " + getGeneralCant() + ", vip = " + getVipCant() + " ) asientos ( usados = " +  usedSeats() + ", total = " + totalCapacity() + ", restantes = " + totalRermainigCapacity() + " )";
	}
}
