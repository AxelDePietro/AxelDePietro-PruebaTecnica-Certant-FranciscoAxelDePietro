package com.axel.pruebatecnica.api.mapper;

import com.axel.pruebatecnica.api.dto.EventCreateDTO;
import com.axel.pruebatecnica.api.entity.EventEntity;
import com.axel.pruebatecnica.api.entity.SeatCapacityEntity;
import com.axel.pruebatecnica.api.entity.enums.EventTypeEnum;
import com.axel.pruebatecnica.api.entity.enums.SeatTypeEnum;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EventMapper {
    
    public static EventEntity fromDTO(EventCreateDTO dto) {

        EventEntity event = new EventEntity();
        
        //setea datos del dto a la entidad
        event.setName(dto.getName());
        event.setDateTime(LocalDateTime.parse(dto.getDateTime()));
        event.setEventTypeEnum(EventTypeEnum.valueOf(dto.getEventType()));

        //lista de seatCapacity
        List<SeatCapacityEntity> capacities = new ArrayList<>();

        //toma evetType para comparar el enum y crear las entidades seatType correspondientes
        switch (event.getEventTypeEnum()) {

        	//en caso de que sea teatro agregan las entidades a la lista de SeatCapacity, y lo mismo con las demas
            case THEATER -> {
                if (dto.getTheaterGeneral() != null)
                    capacities.add(buildCapacity(SeatTypeEnum.OBRA_ENTRADA_GENERAL, dto.getTheaterGeneral(), event));

                if (dto.getTheaterVip() != null)
                    capacities.add(buildCapacity(SeatTypeEnum.OBRA_ENTRADA_VIP, dto.getTheaterVip(), event));
            }

            case CONCERT -> {
                if (dto.getConcertPlatea() != null)
                    capacities.add(buildCapacity(SeatTypeEnum.RECITAL_PLATEA, dto.getConcertPlatea(), event));

                if (dto.getConcertCampo() != null)
                    capacities.add(buildCapacity(SeatTypeEnum.RECITAL_CAMPO, dto.getConcertCampo(), event));

                if (dto.getConcertPalco() != null)
                    capacities.add(buildCapacity(SeatTypeEnum.RECITAL_PALCO, dto.getConcertPalco(), event));
            }

            case CONFERENCE -> {
                if (dto.getConferenceSeatsMyG() != null)
                    capacities.add(buildCapacity(SeatTypeEnum.CHARLA_CON_MEET, dto.getConferenceSeatsMyG(), event));
                
                if (dto.getConferenceSeatsNoMyG() != null)
                    capacities.add(buildCapacity(SeatTypeEnum.CHARLA_SIN_MEET, dto.getConferenceSeatsNoMyG(), event));
            }
        }
        
        //setea entidad de lista seatCapacity
        event.setSeatCapacity(capacities);
        return event;
    }

    //metodo para crear/buildear entidad seatCapacity, esta se añade al array de tipo de asientos de la entidad Event
    private static SeatCapacityEntity buildCapacity(SeatTypeEnum type, Integer value, EventEntity event) {
        SeatCapacityEntity sc = new SeatCapacityEntity();
        sc.setSeatType(type);
        sc.setCapacity(value);
        sc.setEvent(event);
        return sc;
    }
    
}