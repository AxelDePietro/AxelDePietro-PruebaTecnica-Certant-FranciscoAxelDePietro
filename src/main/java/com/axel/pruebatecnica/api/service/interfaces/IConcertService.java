package com.axel.pruebatecnica.api.service.interfaces;

import java.util.List;

import com.axel.pruebatecnica.api.entity.envents.EventConcertEntity;

public interface IConcertService {

	public EventConcertEntity createConcert (EventConcertEntity concertEntity);
	
	public List<EventConcertEntity> allConcerts ();
	
	public void delete (int idConcert);
	
}
