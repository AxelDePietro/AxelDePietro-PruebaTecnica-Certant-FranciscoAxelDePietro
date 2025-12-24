package com.axel.pruebatecnica.api.mapper;

import com.axel.pruebatecnica.api.dto.event.theater.TheaterCreateDTO;
import com.axel.pruebatecnica.api.dto.event.theater.TheaterResponseDTO;
import com.axel.pruebatecnica.api.entity.envents.EventTheaterEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TheaterMapper {

    EventTheaterEntity toEntity (TheaterCreateDTO dto);

    TheaterResponseDTO toDTO (EventTheaterEntity entity);

}
