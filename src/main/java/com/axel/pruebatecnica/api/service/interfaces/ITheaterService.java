package com.axel.pruebatecnica.api.service.interfaces;

import java.util.List;

import com.axel.pruebatecnica.api.entity.envents.EventTheaterEntity;

public interface ITheaterService {

	public EventTheaterEntity createTheater(EventTheaterEntity theaterEntity);
	
	public List<EventTheaterEntity> allTheaters ();
	
	public void delete (int idTheater);
	
}
