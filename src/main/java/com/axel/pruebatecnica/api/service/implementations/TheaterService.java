package com.axel.pruebatecnica.api.service.implementations;

import java.util.List;

import org.springframework.stereotype.Service;

import com.axel.pruebatecnica.api.entity.envents.EventTheaterEntity;
import com.axel.pruebatecnica.api.repository.ITheaterRepository;
import com.axel.pruebatecnica.api.service.interfaces.ITheaterService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TheaterService implements ITheaterService{

	private final ITheaterRepository theaterRepository;

	@Override
	public EventTheaterEntity createTheater(EventTheaterEntity theaterEntity) {
		return theaterRepository.save(theaterEntity);
	}

	@Override
	public List<EventTheaterEntity> allTheaters() {
		return theaterRepository.findAll();
	}

	@Override
	public void delete(int idTheater) {
		theaterRepository.deleteById(idTheater);
	}
	
}
