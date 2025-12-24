package com.axel.pruebatecnica.api.mapper;

import com.axel.pruebatecnica.api.dto.event.concert.ConcertCreateDTO;
import com.axel.pruebatecnica.api.dto.event.conference.ConferenceCreateDTO;
import com.axel.pruebatecnica.api.dto.event.conference.ConferenceResponseDTO;
import com.axel.pruebatecnica.api.entity.envents.EventConferenceEntity;
import org.mapstruct.Mapper;

@Mapper (componentModel = "spring")
public interface ConferenceMapper {

    EventConferenceEntity toEntity (ConferenceCreateDTO dto);

    ConferenceResponseDTO toDTO (EventConferenceEntity entity);

}
