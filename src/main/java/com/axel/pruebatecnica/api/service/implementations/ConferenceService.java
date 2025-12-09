package com.axel.pruebatecnica.api.service.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.axel.pruebatecnica.api.entity.envents.EventConferenceEntity;
import com.axel.pruebatecnica.api.exceptions.ListaVacia;
import com.axel.pruebatecnica.api.repository.IConferenceRepository;
import com.axel.pruebatecnica.api.service.interfaces.IConferenceService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ConferenceService implements IConferenceService {

	private final IConferenceRepository conferenceRepository;

	@Override
	@Transactional
	public EventConferenceEntity createConference(EventConferenceEntity conferenceEntity) {

		if (conferenceEntity.getName().isEmpty()) {
			throw new RuntimeException("el evento no puede tener en nombre vacio");
		}

		if (conferenceEntity.getDateTime() == null) {
			throw new RuntimeException("el evento no puede tener la fecha vacia");
		}

		return conferenceRepository.save(conferenceEntity);
	}

	@Override
	@Transactional(readOnly = true)
	public List<EventConferenceEntity> allConferences() {

		List<EventConferenceEntity> conferences = conferenceRepository.findAll();

		if (conferences.isEmpty() || conferences == null) {
			throw new ListaVacia();
		}

		return conferences;

	}

	@Override
	@Transactional
	public void delete(int idConference) {
		Optional<EventConferenceEntity> optional = conferenceRepository.findById(idConference);

		if (optional.isEmpty()) {
			throw new RuntimeException("la conferencia que desea eliminar no se encontro");
		}

		conferenceRepository.deleteById(idConference);
	}

}
