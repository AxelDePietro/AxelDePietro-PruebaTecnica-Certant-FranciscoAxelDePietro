package com.api.pruebatecnica_franciscoaxeldepietro;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.axel.pruebatecnica.api.entity.BookingEntity;
import com.axel.pruebatecnica.api.entity.SeatCapacityEntity;
import com.axel.pruebatecnica.api.entity.UserEntity;
import com.axel.pruebatecnica.api.entity.enums.SeatTypeEnum;
import com.axel.pruebatecnica.api.entity.envents.EventEntity;
import com.axel.pruebatecnica.api.repository.IBookingRepository;
import com.axel.pruebatecnica.api.repository.IEventRepository;
import com.axel.pruebatecnica.api.repository.IUserRepository;
import com.axel.pruebatecnica.api.service.implementations.BookingService;

@ExtendWith(MockitoExtension.class)
class BookingServiceTest {

    @Mock
    private IBookingRepository bookingRepository;
    @Mock
    private IEventRepository eventRepository;
    @Mock
    private IUserRepository userRepository;

    @InjectMocks
    private BookingService service;

    private UserEntity user;
    private EventEntity event;
    private BookingEntity booking;
    private SeatCapacityEntity seatCapacity;

    @BeforeEach
    void setUp() {
        user = new UserEntity();
        user.setIdUser(1);
        user.setFreePass(false);
        user.setBookings(new ArrayList<>());

        seatCapacity = new SeatCapacityEntity();
        seatCapacity.setSeatType(SeatTypeEnum.OBRA_ENTRADA_GENERAL);
        seatCapacity.setCapacity(10);

        event = new EventEntity();
        event.setIdEvent(1);
        event.setSeatCapacity(List.of(seatCapacity));

        booking = new BookingEntity();
    }

    @Test
    void crearReservaFallaUsuario() {
        when(eventRepository.findById(1)).thenReturn(Optional.of(event));
        when(userRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> service.create(booking, 1, 1, "OBRA_ENTRADA_GENERAL"));
    }

    @Test
    void crearReservaFallaEvento() {
        when(eventRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> service.create(booking, 1, 1, "OBRA_ENTRADA_GENERAL"));
    }

    @Test
    void crearReservaFallaAsiento() {
        when(eventRepository.findById(1)).thenReturn(Optional.of(event));
        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        assertThrows(RuntimeException.class, () -> service.create(booking, 1, 1, "ASIENTO_INVALIDO"));
    }

    @Test
    void eliminarReservaCorrecto() {
        service.delete(10);
        verify(bookingRepository).deleteById(10);
    }

    @Test
    void listarReservasCorrecta() {
        List<BookingEntity> lista = List.of(new BookingEntity(), new BookingEntity());
        when(bookingRepository.findAll()).thenReturn(lista);

        List<BookingEntity> result = service.bookings();

        assertEquals(2, result.size());
    }

    @Test
    void misReservasCorrecta() {
        BookingEntity b1 = new BookingEntity();
        BookingEntity b2 = new BookingEntity();
        UserEntity other = new UserEntity();

        user.setIdUser(1);
        other.setIdUser(2);
        b1.setUser(user);
        b2.setUser(other);

        when(bookingRepository.findAll()).thenReturn(List.of(b1, b2));

        List<BookingEntity> result = service.myBookings(1);

        assertEquals(1, result.size());
        assertEquals(user, result.get(0).getUser());
    }
}
