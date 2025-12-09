package com.axel.pruebatecnica.api.service.implementations;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException.NotFound;

import com.axel.pruebatecnica.api.entity.envents.EventEntity;
import com.axel.pruebatecnica.api.repository.IEventRepository;
import com.axel.pruebatecnica.api.service.interfaces.IEventService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventService implements IEventService{
	
	private final IEventRepository eventRepository;

	@Override
	@Transactional(readOnly = true)
	public List<EventEntity> allEvents() {
		return eventRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public EventEntity findById(int idEvent) throws Exception {
		return eventRepository.findById(idEvent)
				.orElseThrow(()-> new NotFoundException());
	}
	

}
