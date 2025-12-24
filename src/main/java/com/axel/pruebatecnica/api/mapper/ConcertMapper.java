package com.axel.pruebatecnica.api.mapper;

import com.axel.pruebatecnica.api.dto.event.concert.ConcertCreateDTO;
import com.axel.pruebatecnica.api.dto.event.concert.ConcertResponseDTO;
import com.axel.pruebatecnica.api.entity.envents.EventConcertEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ConcertMapper {


    EventConcertEntity toEntity (ConcertCreateDTO dto);

    ConcertResponseDTO toDTO (EventConcertEntity entity);
}
