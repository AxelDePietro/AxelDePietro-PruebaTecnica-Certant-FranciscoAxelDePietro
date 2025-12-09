package com.axel.pruebatecnica.api.service.implementations;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.axel.pruebatecnica.api.entity.envents.EventConcertEntity;
import com.axel.pruebatecnica.api.exceptions.ListaVacia;
import com.axel.pruebatecnica.api.repository.IConcertRepository;
import com.axel.pruebatecnica.api.service.interfaces.IConcertService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ConcertService implements IConcertService {

	private final IConcertRepository concertRepository;

	@Override
	@Transactional
	public EventConcertEntity createConcert(EventConcertEntity concertEntity) {
		return concertRepository.save(concertEntity);
	}

	@Override
	@Transactional(readOnly = true)
	public List<EventConcertEntity> allConcerts() {
		
		List<EventConcertEntity> concerts = concertRepository.findAll();
		
		if(concerts.isEmpty()) {
			throw new ListaVacia();
		}
		
		return concerts;
	}

	@Override
	@Transactional
	public void delete(int idConcert) {
		concertRepository.deleteById(idConcert);
	}

}
