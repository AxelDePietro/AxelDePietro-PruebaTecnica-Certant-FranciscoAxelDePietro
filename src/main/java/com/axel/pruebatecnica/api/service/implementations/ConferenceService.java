package com.axel.pruebatecnica.api.service.implementations;

import java.util.List;

import org.springframework.stereotype.Service;

import com.axel.pruebatecnica.api.entity.envents.EventConferenceEntity;
import com.axel.pruebatecnica.api.repository.IConferenceRepository;
import com.axel.pruebatecnica.api.service.interfaces.IConferenceService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ConferenceService implements IConferenceService{

	private final IConferenceRepository conferenceRepository;

	@Override
	public EventConferenceEntity createConference(EventConferenceEntity conferenceEntity) {
		return conferenceRepository.save(conferenceEntity);
	}

	@Override
	public List<EventConferenceEntity> allConferences() {
		return conferenceRepository.findAll();
	}

	@Override
	public void delete(int idConference) {
		conferenceRepository.deleteById(idConference);
	}
	
	

}
