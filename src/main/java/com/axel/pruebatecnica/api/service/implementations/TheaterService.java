package com.axel.pruebatecnica.api.service.implementations;

import java.util.List;
import java.util.Optional;

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

	@Override
	@Transactional
	public EventTheaterEntity createTheater(EventTheaterEntity theaterEntity) {

		if (theaterEntity.getName().isEmpty()) {
			throw new RuntimeException("el evento no puede tener en nombre vacio");
		}

		if (theaterEntity.getDateTime() == null) {
			throw new RuntimeException("el evento no puede tener la fecha vacia");
		}

		return theaterRepository.save(theaterEntity);
	}

	@Override
	@Transactional(readOnly = true)
	public List<EventTheaterEntity> allTheaters() {

		List<EventTheaterEntity> theaters = theaterRepository.findAll();

		if (theaters.isEmpty() || theaters == null) {
			throw new ListaVacia();
		}

		return theaters;
	}

	@Override
	@Transactional
	public void delete(int idTheater) {
		Optional<EventTheaterEntity> optional = theaterRepository.findById(idTheater);

		if (optional.isEmpty()) {
			throw new RuntimeException("la obra de teatro que desea eliminar no se encontro");
		}

		theaterRepository.deleteById(idTheater);
	}

}
