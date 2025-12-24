package com.axel.pruebatecnica.api.service.implementations;

import java.util.ArrayList;
import java.util.List;

import com.axel.pruebatecnica.api.dto.event.theater.TheaterCreateDTO;
import com.axel.pruebatecnica.api.dto.event.theater.TheaterResponseDTO;
import com.axel.pruebatecnica.api.exceptions.NoEncontrado;
import com.axel.pruebatecnica.api.mapper.TheaterMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.axel.pruebatecnica.api.entity.envents.EventTheaterEntity;
import com.axel.pruebatecnica.api.exceptions.ListaVacia;
import com.axel.pruebatecnica.api.repository.ITheaterRepository;
import com.axel.pruebatecnica.api.service.interfaces.ITheaterService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TheaterService implements ITheaterService {

	private final ITheaterRepository theaterRepository;
    private final TheaterMapper theaterMapper;

	@Override
	@Transactional
	public EventTheaterEntity createTheater(TheaterCreateDTO theaterDTO) {

        EventTheaterEntity theaterEntity = theaterMapper.toEntity(theaterDTO);

		return theaterRepository.save(theaterEntity);
	}

	@Override
	@Transactional(readOnly = true)
	public List<TheaterResponseDTO> allTheaters() {

		List<TheaterResponseDTO> theaters = new ArrayList<>();

        for(EventTheaterEntity theater : theaterRepository.findAll()) {
            theaters.add(theaterMapper.toDTO(theater));
        }

        //theaters = theaterRepository.findAll().stream().map(t -> theaterMapper.toDTO(t)).toList();

        //theaters = theaterRepository.findAll().stream().map(theaterMapper::toDTO).toList();

        if (theaters.isEmpty()) {
			throw new ListaVacia();
		}

		return theaters;
	}

	@Override
	@Transactional
	public void delete(int idTheater) {

        try {
            theaterRepository.deleteById(idTheater);
        } catch (Exception e) {
            throw new NoEncontrado(idTheater);
        }

	}

}
