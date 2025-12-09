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

import com.axel.pruebatecnica.api.entity.envents.EventTheaterEntity;
import com.axel.pruebatecnica.api.exceptions.ListaVacia;
import com.axel.pruebatecnica.api.repository.ITheaterRepository;
import com.axel.pruebatecnica.api.service.implementations.TheaterService;

@ExtendWith(MockitoExtension.class)
public class TheaterServiceTest {

	@Mock
	ITheaterRepository theaterRepository;

	@InjectMocks
	TheaterService theaterService;

	EventTheaterEntity eventTheaterEntity;

	@BeforeEach
	void init() {

		eventTheaterEntity = new EventTheaterEntity();
		eventTheaterEntity.setIdEvent(1);
		eventTheaterEntity.setName("concierto1");
		eventTheaterEntity.setDateTime(LocalDateTime.now());
		eventTheaterEntity.setGeneralCant(100);
		eventTheaterEntity.setVipCant(100);

	}

	// "happy path" 
	
	@Test
	@DisplayName("crear conferencia correctamente")
	void createConcert() {

		when(theaterRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

		EventTheaterEntity concert = theaterService.createTheater(eventTheaterEntity);

		assertNotNull(concert);
		assertEquals(concert, eventTheaterEntity);

		verify(theaterRepository).save(eventTheaterEntity);

	}

	@Test
	@DisplayName("buscar lista de conferencia correctamente")
	void allConcerts() {

		when(theaterRepository.findAll())
				.thenReturn(Arrays.asList(eventTheaterEntity, new EventTheaterEntity(), new EventTheaterEntity()));

		List<EventTheaterEntity> conferenceEntities = theaterService.allTheaters();

		assertEquals(eventTheaterEntity, conferenceEntities.get(0));

		assertFalse(conferenceEntities.isEmpty());

		assertEquals(3, conferenceEntities.size());
		

	}
	
	@Test
	@DisplayName("eliminar conferencia correctamente")
	void delete(){
		
		//enmende croto a optional
		Optional<EventTheaterEntity> optional = Optional.of(eventTheaterEntity);
		
		when(theaterRepository.findById(1)).thenReturn(optional);
		
		theaterService.delete(1);
		
		verify(theaterRepository, times(1)).deleteById(1);;
				
		
	}

	// "Unhappy path"
	
	@Test
	@DisplayName("crear conferencia falla por nombre")
	void createConcertNameFailure() {

		eventTheaterEntity.setName("");

		Exception ex = assertThrows(RuntimeException.class, () -> theaterService.createTheater(eventTheaterEntity));
		
		assertEquals("el evento no puede tener en nombre vacio", ex.getMessage());
		
	}
	
	@Test
	@DisplayName("crear conferencia falla por fecha")
	void createConcertDateFailure() {

		eventTheaterEntity.setDateTime(null);

		Exception ex = assertThrows(RuntimeException.class, () -> theaterService.createTheater(eventTheaterEntity));
		
		assertEquals("el evento no puede tener la fecha vacia", ex.getMessage());
		
	}
	
	@Test
	@DisplayName("buscar lista falla por lista vacia")
	void allConferencesFailure() {

		when(theaterRepository.findAll())
				.thenReturn(Collections.emptyList() );

		Exception ex = assertThrows(ListaVacia.class,  () -> theaterService.allTheaters());

		assertEquals("la lista no tiene contenido", ex.getMessage());
		
	}
	
	@Test
	@DisplayName("delete Falla por Id invalido")
	void deleteFailure() {
		
		when(theaterRepository.findById(anyInt())).thenReturn(Optional.empty());
		
		Exception ex = assertThrows(RuntimeException.class , () -> theaterService.delete(anyInt()));
		
		assertEquals("la obra de teatro que desea eliminar no se encontro", ex.getMessage());
		
		verify(theaterRepository, never()).deleteById(2);
		
	}
	
}
