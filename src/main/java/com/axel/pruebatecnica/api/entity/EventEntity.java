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

    
    //tipo de evento
    @Column(name = "event_type")
    @Enumerated(EnumType.STRING)
    private EventTypeEnum eventTypeEnum;

    //realaciones
    
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SeatCapacityEntity> seatCapacity = new ArrayList<>();

    @OneToMany(mappedBy = "event", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = false)
    private List<BookingEntity> bookings = new ArrayList<>();

    //todos estos se usan como valores en las vistas
    //totalidad ed asientos
    @Transient
    public int getTotalCapacity() {
        return seatCapacity.stream()
        		//convierte cada capacidad de esta entidad a entero
                .mapToInt(SeatCapacityEntity::getCapacity)
                //los suma 
                .sum();
    }

    //asientos usados, mide la lista de reservas
    @Transient
    public int getUsedSeats() {
        return bookings.size();
    }

    //disponibilidad, resta al total el largo de la lista
    @Transient
    public int getAvailableSeats() {
        return getTotalCapacity() - getUsedSeats();
    }
}
