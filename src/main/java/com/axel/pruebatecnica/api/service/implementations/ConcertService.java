package com.axel.pruebatecnica.api.service.implementations;

import java.util.List;
import java.util.Optional;

import com.axel.pruebatecnica.api.dto.event.concert.ConcertCreateDTO;
import com.axel.pruebatecnica.api.mapper.ConcertMapper;
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
    private final ConcertMapper concertMapper;

	@Override
	@Transactional
	public EventConcertEntity createConcert(ConcertCreateDTO concertDTO) {
		
		EventConcertEntity concertEntity = concertMapper.toEntity(concertDTO);
		
		return concertRepository.save(concertEntity);
	}

	@Override
	@Transactional(readOnly = true)
	public List<EventConcertEntity> allConcerts() {

		List<EventConcertEntity> concerts = concertRepository.findAll();

		if (concerts.isEmpty() || concerts == null) {
			throw new ListaVacia();
		}

		return concerts;
	}

	@Override
	@Transactional
	public void delete(int idConcert) {
		
		Optional<EventConcertEntity> optional = concertRepository.findById(idConcert); 
		
		if(optional.isEmpty()) {
			throw new RuntimeException("el concierto que desea eliminar no se encontro");
		}
		
		concertRepository.deleteById(idConcert);
	}

}
