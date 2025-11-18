package com.axel.pruebatecnica.api.service.implementations;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.axel.pruebatecnica.api.entity.envents.EventTheaterEntity;
import com.axel.pruebatecnica.api.repository.ITheaterRepository;
import com.axel.pruebatecnica.api.service.interfaces.ITheaterService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TheaterService implements ITheaterService{

	private final ITheaterRepository theaterRepository;

	@Override
    @Transactional
	public EventTheaterEntity createTheater(EventTheaterEntity theaterEntity) {
		return theaterRepository.save(theaterEntity);
	}

	@Override
	@Transactional(readOnly = true)
	public List<EventTheaterEntity> allTheaters() {
		return theaterRepository.findAll();
	}

	@Override
	@Transactional
	public void delete(int idTheater) {
		theaterRepository.deleteById(idTheater);
	}
	
}
