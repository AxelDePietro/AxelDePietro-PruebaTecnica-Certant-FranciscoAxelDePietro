package com.axel.pruebatecnica.api.service.implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.axel.pruebatecnica.api.dto.event.conference.ConferenceCreateDTO;
import com.axel.pruebatecnica.api.dto.event.conference.ConferenceResponseDTO;
import com.axel.pruebatecnica.api.exceptions.NoEncontrado;
import com.axel.pruebatecnica.api.mapper.ConferenceMapper;
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
    private final ConferenceMapper conferenceMapper;

	@Override
	@Transactional
	public EventConferenceEntity createConference(ConferenceCreateDTO conferenceDTO) {

        EventConferenceEntity conferenceEntity = conferenceMapper.toEntity(conferenceDTO);

		return conferenceRepository.save(conferenceEntity);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ConferenceResponseDTO> allConferences() {

		List<ConferenceResponseDTO> conferences = new ArrayList<>();

        conferences = conferenceRepository.findAll().stream().map(conferenceMapper::toDTO).toList();

		if (conferences.isEmpty()) {
			throw new ListaVacia();
		}

		return conferences;

	}

	@Override
	@Transactional
	public void delete(int idConference) {

        try {
            conferenceRepository.findById(idConference);
        } catch (Exception e) {
            throw new NoEncontrado(idConference);
        }

		conferenceRepository.deleteById(idConference);
	}

}
