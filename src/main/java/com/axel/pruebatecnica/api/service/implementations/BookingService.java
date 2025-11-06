package com.axel.pruebatecnica.api.service.implementations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.axel.pruebatecnica.api.entity.BookingEntity;
import com.axel.pruebatecnica.api.entity.EventEntity;
import com.axel.pruebatecnica.api.entity.SeatCapacityEntity;
import com.axel.pruebatecnica.api.entity.UserEntity;
import com.axel.pruebatecnica.api.entity.enums.SeatTypeEnum;
import com.axel.pruebatecnica.api.repository.IBookingRepository;
import com.axel.pruebatecnica.api.repository.IEventRepository;
import com.axel.pruebatecnica.api.repository.IUserRepository;
import com.axel.pruebatecnica.api.service.IBookingService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookingService implements IBookingService {
    private final IBookingRepository bookingRepository;
    private final IEventRepository eventRepository;
    private final IUserRepository userRepository;

    //crear reserva
    @Override
    public BookingEntity create(BookingEntity booking, int idEvent, int idClient, String seatTypeString) {

        EventEntity event = eventRepository.findById(idEvent)
                .orElseThrow(() -> new RuntimeException("Evento no encontrado: " + idEvent));

        UserEntity user = userRepository.findById(idClient)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado: " + idClient));

        SeatTypeEnum seatType = parseSeatType(seatTypeString);

        SeatCapacityEntity seatCapacity = findSeatCapacity(event, seatType);

        seatCapacity.setCapacity(seatCapacity.getCapacity() - 1);

        booking.setEvent(event);
        booking.setUser(user);
        booking.setPrice(seatType.getValor());

        //actualiza el evento
        eventRepository.save(event);
        
        return bookingRepository.save(booking);
    }

    //todasd las reservas
    @Override
    public List<BookingEntity> bookings() {

        return bookingRepository.findAll();
    }

    //reservas por usuario
    public List<BookingEntity> myBookings(int idUser) {

        List<BookingEntity> bookings = new ArrayList<>();

        for (BookingEntity b : bookingRepository.findAll()) {
            if (b.getUser().getIdUser() == idUser) {
                bookings.add(b);
            }
        }

        return bookings;
    }

    //eliminr
    @Override
    public void delete(int idBooking) {

        bookingRepository.deleteById(idBooking);

    }
    
    //metodos para uso local del creart
    private SeatTypeEnum parseSeatType(String seat) {
        try {
            return SeatTypeEnum.valueOf(seat.toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new RuntimeException("Tipo de asiento inválido: " + seat);
        }
    }

    private SeatCapacityEntity findSeatCapacity(EventEntity event, SeatTypeEnum seatType) {
        return event.getSeatCapacity()
                .stream()
                .filter(seatCap -> seatCap.getSeatType() == seatType)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("El evento no tiene capacidad para " + seatType));
    }

}
