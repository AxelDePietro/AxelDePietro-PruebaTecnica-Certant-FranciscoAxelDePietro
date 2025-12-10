package com.axel.pruebatecnica.api.services;

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

import com.axel.pruebatecnica.api.entity.envents.EventConcertEntity;
import com.axel.pruebatecnica.api.exceptions.ListaVacia;
import com.axel.pruebatecnica.api.repository.IConcertRepository;
import com.axel.pruebatecnica.api.service.implementations.ConcertService;

@ExtendWith(MockitoExtension.class)
public class ConcertServiceTest {

	@Mock
	IConcertRepository concertRepository;

	@InjectMocks
	ConcertService concertService;

	EventConcertEntity eventConcertEntity;

	@BeforeEach
	void init() {

		eventConcertEntity = new EventConcertEntity();
		eventConcertEntity.setIdEvent(1);
		eventConcertEntity.setName("concierto1");
		eventConcertEntity.setDateTime(LocalDateTime.now());
		eventConcertEntity.setCampoCant(100);
		eventConcertEntity.setPalcoCant(100);
		eventConcertEntity.setPlateaCant(100);

	}

	// "happy path" 
	
	@Test
	@DisplayName("crear concierto correctamente")
	void createConcert() {

		when(concertRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

		EventConcertEntity concert = concertService.createConcert(eventConcertEntity);

		assertNotNull(concert);
		assertEquals(concert, eventConcertEntity);

		verify(concertRepository).save(eventConcertEntity);

	}

	@Test
	@DisplayName("buscar lista de conciertos correctamente")
	void allConcerts() {

		when(concertRepository.findAll())
				.thenReturn(Arrays.asList(eventConcertEntity, new EventConcertEntity(), new EventConcertEntity()));

		List<EventConcertEntity> concertEntities = concertService.allConcerts();

		assertEquals(eventConcertEntity, concertEntities.get(0));

		assertFalse(concertEntities.isEmpty());

		assertEquals(3, concertEntities.size());
		

	}
	
	@Test
	@DisplayName("eliminar concierto correctamente")
	void delete(){
		
		//enmende croto a optional
		Optional<EventConcertEntity> optional = Optional.of(eventConcertEntity);
		
		when(concertRepository.findById(1)).thenReturn(optional);
		
		concertService.delete(1);
		
		verify(concertRepository, times(1)).deleteById(1);;
				
		
	}

	// "Unhappy path"
	
	@Test
	@DisplayName("crear concierto falla por nombre")
	void createConcertNameFailure() {

		eventConcertEntity.setName("");

		Exception ex = assertThrows(RuntimeException.class, () -> concertService.createConcert(eventConcertEntity));
		
		assertEquals("el evento no puede tener en nombre vacio", ex.getMessage());
		
	}
	
	@Test
	@DisplayName("crear concierto falla por fecha")
	void createConcertDateFailure() {

		eventConcertEntity.setDateTime(null);

		Exception ex = assertThrows(RuntimeException.class, () -> concertService.createConcert(eventConcertEntity));
		
		assertEquals("el evento no puede tener la fecha vacia", ex.getMessage());
		
	}
	
	@Test
	@DisplayName("buscar lista falla por lista vacia")
	void allConcertsFailure() {

		when(concertRepository.findAll())
				.thenReturn(Collections.emptyList() );

		Exception ex = assertThrows(ListaVacia.class,  () -> concertService.allConcerts());

		assertEquals("la lista no tiene contenido", ex.getMessage());
		
	}
	
	@Test
	@DisplayName("delete Falla por Id invalido")
	void deleteFailure() {
		
		when(concertRepository.findById(anyInt())).thenReturn(Optional.empty());
		
		Exception ex = assertThrows(RuntimeException.class , () -> concertService.delete(anyInt()));
		
		assertEquals("el concierto que desea eliminar no se encontro", ex.getMessage());
		
		verify(concertRepository, never()).deleteById(2);
		
	}
	
	
}
