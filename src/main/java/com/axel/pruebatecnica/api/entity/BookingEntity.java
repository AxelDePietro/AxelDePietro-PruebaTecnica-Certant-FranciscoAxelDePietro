package com.axel.pruebatecnica.api.entity;

import com.axel.pruebatecnica.api.entity.envents.EventEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookingEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_booking")
	private int idBooking;

    @Column(name = "booking_price")
	private int price;

    @ManyToOne
    @JoinColumn(name = "idUser")
    private UserEntity user;
    
    @ManyToOne
    @JoinColumn(name = "idEvent")
    private EventEntity  eventEntity;
    
}
