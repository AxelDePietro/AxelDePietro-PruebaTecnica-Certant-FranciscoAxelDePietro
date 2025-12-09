package com.axel.pruebatecnica.api.service.implementations;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.axel.pruebatecnica.api.entity.envents.EventEntity;
import com.axel.pruebatecnica.api.exceptions.ListaVacia;
import com.axel.pruebatecnica.api.repository.IEventRepository;
import com.axel.pruebatecnica.api.service.interfaces.IEventService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventService implements IEventService {

	private final IEventRepository eventRepository;

	@Override
	@Transactional(readOnly = true)
	public List<EventEntity> allEvents() {

		List<EventEntity> eventos = eventRepository.findAll();

		if (eventos.isEmpty() || eventos == null) {
			throw new ListaVacia();
		}

		return eventos;
	}

	@Override
	@Transactional(readOnly = true)
	public EventEntity findById(int idEvent) throws Exception {
		return eventRepository.findById(idEvent).orElseThrow(() -> new NotFoundException());
	}

}
