package com.axel.pruebatecnica.api.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
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

import com.axel.pruebatecnica.api.entity.BookingEntity;
import com.axel.pruebatecnica.api.entity.UserEntity;
import com.axel.pruebatecnica.api.entity.enums.SeatTypeEnum;
import com.axel.pruebatecnica.api.entity.envents.EventConcertEntity;
import com.axel.pruebatecnica.api.exceptions.SinReservas;
import com.axel.pruebatecnica.api.repository.IBookingRepository;
import com.axel.pruebatecnica.api.repository.IEventRepository;
import com.axel.pruebatecnica.api.repository.IUserRepository;
import com.axel.pruebatecnica.api.service.implementations.BookingService;

@ExtendWith(MockitoExtension.class)
class BookingServiceTest {

	@Mock
	IBookingRepository bookingRepository;
	@Mock
	IEventRepository eventRepository;
	@Mock
	IUserRepository userRepository;

	@InjectMocks
	BookingService bookingService;

	EventConcertEntity eventConcertEntity;
	BookingEntity bookingEntity;
	UserEntity userEntity;

	@BeforeEach
	void init() {
		eventConcertEntity = new EventConcertEntity();
		eventConcertEntity.setIdEvent(1);
		eventConcertEntity.setCampoCant(100);
		eventConcertEntity.setPalcoCant(100);
		eventConcertEntity.setPlateaCant(100);

		bookingEntity = new BookingEntity();
		bookingEntity.setIdBooking(1);
		bookingEntity.setSeatType(SeatTypeEnum.RECITAL_CAMPO);

		userEntity = new UserEntity();
		userEntity.setIdUser(1);
		userEntity.setBookings(
				new ArrayList<>(Arrays.asList(new BookingEntity(), new BookingEntity(), new BookingEntity())));

	}

	///Happy Path
	@Test
	@DisplayName("crear correctamente una reserva")
	void createBookings() throws Exception {

		when(eventRepository.findById(1)).thenReturn(Optional.of(eventConcertEntity));
		when(userRepository.findById(1)).thenReturn(Optional.of(userEntity));
		when(bookingRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

		BookingEntity result = bookingService.createBooking(bookingEntity, 1, 1);

		// verificamos que los parametros no sean nulos
		// guardo una reserva
		assertNotNull(result);
		assertNotNull(result.getUser());
		assertNotNull(result.getEvent());

		assertEquals(1, result.getIdBooking());
		assertEquals(1, result.getEvent().getIdEvent());
		assertEquals(1, result.getUser().getIdUser());
		assertEquals(99, eventConcertEntity.getCampoCant());

		// probamos que usuario tenga free pass
		assertFalse(userEntity.isFreePass());
		assertTrue(bookingEntity.getPrice() > 0);

		// verificaciones
		verify(bookingRepository).save(bookingEntity);
		verify(eventRepository).findById(1);
		verify(userRepository).findById(1);

	}

	@Test
	@DisplayName("crear correctamente una reserva con free pass de user")
	void createBookingsFreePass() throws Exception {

		when(eventRepository.findById(1)).thenReturn(Optional.of(eventConcertEntity));
		when(userRepository.findById(1)).thenReturn(Optional.of(userEntity));
		when(bookingRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

		// agregamos una cuarta reserva para activar freepass
		userEntity.getBookings().add(new BookingEntity());

		BookingEntity result = bookingService.createBooking(bookingEntity, 1, 1);

		// verificamos que los parametros no sean nulos
		// guardo una reserva
		assertNotNull(result);
		assertNotNull(result.getUser());
		assertNotNull(result.getEvent());

		assertEquals(1, result.getIdBooking());
		assertEquals(1, result.getEvent().getIdEvent());
		assertEquals(1, result.getUser().getIdUser());
		assertEquals(99, eventConcertEntity.getCampoCant());

		// probamos free pass
		assertTrue(userEntity.isFreePass());
		assertEquals(0, bookingEntity.getPrice());

		// verificaciones
		verify(bookingRepository).save(bookingEntity);
		verify(eventRepository).findById(1);
		verify(userRepository).findById(1);

	}

	@Test
	@DisplayName("lista de reservas se encuentra correctamente")
	void allBookings() throws Exception {

		when(bookingRepository.findAll())
				.thenReturn(Arrays.asList(bookingEntity, new BookingEntity(), new BookingEntity()));

		List<BookingEntity> result = bookingService.allBookings();

		assertFalse(result.isEmpty());
		assertEquals(3, result.size());
	}

	@Test
	@DisplayName("lista de reservas por usuario se encuentra")
	void myBookings() {
		// entidades
		BookingEntity b1 = new BookingEntity();
		BookingEntity b2 = new BookingEntity();
		BookingEntity b3 = new BookingEntity();
		BookingEntity b4 = new BookingEntity();

		UserEntity other = new UserEntity();

		// set de entidades
		other.setIdUser(2);
		other.setBookings(Arrays.asList(b1, b2));

		// se setea un usuario en todos para que al filtrar en el metod no de
		// nullpointer
		b1.setUser(other);
		b2.setUser(other);
		b3.setUser(userEntity);
		b4.setUser(userEntity);

		when(bookingRepository.findAll()).thenReturn(Arrays.asList(b1, b2, b3, b4));

		List<BookingEntity> result = bookingService.myBookings(1);

		// verificaciones
		assertFalse(result.isEmpty());
		assertEquals(2, result.size());
	}

	@Test
	@DisplayName("eliminar reserva por id")
	void deleteBooking() {
		// se llama el delete del servicio
		bookingService.delete(1);
		// verificamos que se llame al repositorio dentro del service
		verify(bookingRepository).deleteById(1);

	}

	///Unhappy Path
	@Test
	@DisplayName("crear una reserva con evento empty ")
	void createBookingsBadRequestEvent() throws Exception {

		when(eventRepository.findById(1)).thenReturn(Optional.empty());

		RuntimeException ex = assertThrows(RuntimeException.class,
				() -> bookingService.createBooking(bookingEntity, 1, 1));
		assertEquals("el evento no se encontro", ex.getMessage());
	}

	@Test
	@DisplayName("crear una reserva con usuario empty ")
	void createBookingsBadRequestUser() throws Exception {

		when(eventRepository.findById(1)).thenReturn(Optional.of(eventConcertEntity));
		when(userRepository.findById(1)).thenReturn(Optional.empty());

		RuntimeException ex = assertThrows(RuntimeException.class,
				() -> bookingService.createBooking(bookingEntity, 1, 1));
		assertEquals("el usuario no se encontro", ex.getMessage());
	}

	@Test
	@DisplayName("lista de reservas, lista vacia")
	void allBookingsFailure() throws Exception {

		when(bookingRepository.findAll())
				.thenReturn(Collections.emptyList());

		Exception ex = assertThrows(SinReservas.class, () -> bookingService.allBookings());
		
		assertEquals("el listado de reservas esta vacio", ex.getMessage());
		
	}
	
	@Test
	@DisplayName("lista de reservas por usuario, lista vacia")
	void myBookingsFailure() {

		//otra forma de pasar lista vacia :)
		when(bookingRepository.findAll()).thenReturn(Arrays.asList());

		Exception ex = assertThrows(SinReservas.class, ()-> bookingService.myBookings(1));
		
		assertEquals("el listado de reservas esta vacio", ex.getMessage());
	
	}

	
	
}

















