package com.axel.pruebatecnica.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.axel.pruebatecnica.api.entity.envents.EventConferenceEntity;
import com.axel.pruebatecnica.api.exceptions.ListaVacia;
import com.axel.pruebatecnica.api.repository.IConferenceRepository;
import com.axel.pruebatecnica.api.service.implementations.ConferenceService;

@ExtendWith(MockitoExtension.class)
public class ConferenceServiceTest {

	@Mock
	IConferenceRepository conferenceRepository;

	@InjectMocks
	ConferenceService conferenceService;

	EventConferenceEntity evenConferenceEntity;

	@BeforeEach
	void init() {

		evenConferenceEntity = new EventConferenceEntity();
		evenConferenceEntity.setIdEvent(1);
		evenConferenceEntity.setName("concierto1");
		evenConferenceEntity.setDateTime(LocalDateTime.now());
		evenConferenceEntity.setCharlaConMeetCant(100);
		evenConferenceEntity.setCharlaSinMeetCant(100);

	}

	// "happy path" 
	
	@Test
	@DisplayName("crear conferencia correctamente")
	void createConcert() {

		when(conferenceRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

		EventConferenceEntity concert = conferenceService.createConference(evenConferenceEntity);

		assertNotNull(concert);
		assertEquals(concert, evenConferenceEntity);

		verify(conferenceRepository).save(evenConferenceEntity);

	}

	@Test
	@DisplayName("buscar lista de conferencia correctamente")
	void allConcerts() {

		when(conferenceRepository.findAll())
				.thenReturn(Arrays.asList(evenConferenceEntity, new EventConferenceEntity(), new EventConferenceEntity()));

		List<EventConferenceEntity> conferenceEntities = conferenceService.allConferences();

		assertEquals(evenConferenceEntity, conferenceEntities.get(0));

		assertFalse(conferenceEntities.isEmpty());

		assertEquals(3, conferenceEntities.size());
		

	}
	
	@Test
	@DisplayName("eliminar conferencia correctamente")
	void delete(){
		
		//enmende croto a optional
		Optional<EventConferenceEntity> optional = Optional.of(evenConferenceEntity);
		
		when(conferenceRepository.findById(1)).thenReturn(optional);
		
		conferenceService.delete(1);
		
		verify(conferenceRepository, times(1)).deleteById(1);;
				
		
	}

	// "Unhappy path"
	
	@Test
	@DisplayName("crear conferencia falla por nombre")
	void createConcertNameFailure() {

		evenConferenceEntity.setName("");

		Exception ex = assertThrows(RuntimeException.class, () -> conferenceService.createConference(evenConferenceEntity));
		
		assertEquals("el evento no puede tener en nombre vacio", ex.getMessage());
		
	}
	
	@Test
	@DisplayName("crear conferencia falla por fecha")
	void createConcertDateFailure() {

		evenConferenceEntity.setDateTime(null);

		Exception ex = assertThrows(RuntimeException.class, () -> conferenceService.createConference(evenConferenceEntity));
		
		assertEquals("el evento no puede tener la fecha vacia", ex.getMessage());
		
	}
	
	@Test
	@DisplayName("buscar lista falla por lista vacia")
	void allConferencesFailure() {

		when(conferenceRepository.findAll())
				.thenReturn(Collections.emptyList() );

		Exception ex = assertThrows(ListaVacia.class,  () -> conferenceService.allConferences());

		assertEquals("la lista no tiene contenido", ex.getMessage());
		
	}
	
	@Test
	@DisplayName("delete Falla por Id invalido")
	void deleteFailure() {
		
		when(conferenceRepository.findById(anyInt())).thenReturn(Optional.empty());
		
		Exception ex = assertThrows(RuntimeException.class , () -> conferenceService.delete(anyInt()));
		
		assertEquals("la conferencia que desea eliminar no se encontro", ex.getMessage());
		
		verify(conferenceRepository, never()).deleteById(2);
		
	}
	
}
