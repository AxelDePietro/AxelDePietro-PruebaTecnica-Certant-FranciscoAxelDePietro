package com.axel.pruebatecnica.api.service.interfaces;

import java.util.List;

import com.axel.pruebatecnica.api.dto.event.concert.ConcertCreateDTO;
import com.axel.pruebatecnica.api.entity.envents.EventConcertEntity;

public interface IConcertService {

	public EventConcertEntity createConcert(ConcertCreateDTO concertDTO);

	public List<EventConcertEntity> allConcerts();

	public void delete(int idConcert);

}
