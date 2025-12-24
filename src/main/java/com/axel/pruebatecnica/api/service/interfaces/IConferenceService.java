package com.axel.pruebatecnica.api.service.interfaces;

import java.util.List;

import com.axel.pruebatecnica.api.dto.event.conference.ConferenceCreateDTO;
import org.springframework.stereotype.Service;

import com.axel.pruebatecnica.api.entity.envents.EventConferenceEntity;

@Service
public interface IConferenceService {

	public EventConferenceEntity createConference(ConferenceCreateDTO conferenceDTO);

	public List<EventConferenceEntity> allConferences();

	public void delete(int idConference);

}
