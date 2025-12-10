package com.axel.pruebatecnica.api.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.axel.pruebatecnica.api.entity.envents.EventConcertEntity;
import com.axel.pruebatecnica.api.entity.envents.EventEntity;
import com.axel.pruebatecnica.api.repository.IEventRepository;
import com.axel.pruebatecnica.api.service.implementations.EventService;

@ExtendWith(MockitoExtension.class)
public class EventServiceTest {

	@Mock
	IEventRepository eventRepository;
	
	@InjectMocks
	EventService service;
	
	EventConcertEntity concertEntity;
	
	@BeforeEach
	void init() {
		concertEntity = new EventConcertEntity();
		
	}
	
	
	///Happy Path
	@Test
	@DisplayName("find eveto por id correctamente")
	void findById() throws Exception {
		
		//atado con alambre para que sea optinal
		when(eventRepository.findById(anyInt())).thenReturn(Optional.of(concertEntity));
		
		EventEntity result = service.findById(1);
		
		assertEquals(concertEntity, result);
		
		assertTrue(result!=null);
		
		assertTrue(result instanceof EventConcertEntity);
	}
	
	///Unhappy Path
	
	@Test
	@DisplayName("find eveto por id, evento vacio")
	void findByIdFailure() throws Exception {
		
		//atado con alambre para que sea optinal
		when(eventRepository.findById(anyInt())).thenReturn(Optional.empty());
		
		Exception ex = assertThrows(NotFoundException.class, ()-> service.findById(1)); 
		
		assertNotNull(ex);
	}
	
}
