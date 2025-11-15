package com.axel.pruebatecnica.api.service.implementations;

import java.util.List;

import org.springframework.stereotype.Service;

import com.axel.pruebatecnica.api.entity.envents.EventEntity;
import com.axel.pruebatecnica.api.repository.IEventRepository;
import com.axel.pruebatecnica.api.service.interfaces.IEventService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventService implements IEventService{
	
	private final IEventRepository eventRepository;

	@Override
	public List<EventEntity> allEvents() {
		return eventRepository.findAll();
	}
	

}
