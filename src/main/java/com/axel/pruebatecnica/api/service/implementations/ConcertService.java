package com.axel.pruebatecnica.api.service.implementations;

import java.util.List;
import java.util.Optional;

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
		
		if (concertEntity.getName().isEmpty()) {
			throw new RuntimeException("el evento no puede tener en nombre vacio");
		}
		
		if (concertEntity.getDateTime()==null ) {
			throw new RuntimeException("el evento no puede tener la fecha vacia");
		}
		
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
