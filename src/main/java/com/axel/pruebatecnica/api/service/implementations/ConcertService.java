package com.axel.pruebatecnica.api.service.implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.axel.pruebatecnica.api.dto.event.concert.ConcertCreateDTO;
import com.axel.pruebatecnica.api.dto.event.concert.ConcertResponseDTO;
import com.axel.pruebatecnica.api.exceptions.NoEncontrado;
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
	public List<ConcertResponseDTO> allConcerts() {

		List<ConcertResponseDTO> concerts = new ArrayList<>();
        concerts = concertRepository.findAll().stream().map(concertMapper::toDTO).toList();

		if (concerts.isEmpty()) {
			throw new ListaVacia();
		}

		return concerts;
	}

	@Override
	@Transactional
	public void delete(int idConcert) {

        try {
            concertRepository.findById(idConcert);
        } catch (Exception e) {
            throw new NoEncontrado(idConcert);
        }
		
		concertRepository.deleteById(idConcert);
	}

}
