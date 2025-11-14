package com.axel.pruebatecnica.api.service.implementations;

import org.springframework.stereotype.Service;

import com.axel.pruebatecnica.api.repository.IEventRepository;
import com.axel.pruebatecnica.api.service.interfaces.IEventService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventService implements IEventService{
	
	private final IEventRepository eventRepository;
	

}
