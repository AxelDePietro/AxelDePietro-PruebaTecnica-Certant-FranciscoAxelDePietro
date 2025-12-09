package com.axel.pruebatecnica.api.service.interfaces;

import java.util.List;

import com.axel.pruebatecnica.api.entity.envents.EventEntity;

public interface IEventService {

	public EventEntity findById(int idEvent) throws Exception;

	public List<EventEntity> allEvents();

}
