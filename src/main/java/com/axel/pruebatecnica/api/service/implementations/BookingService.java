package com.axel.pruebatecnica.api.service.implementations;

import java.util.List;

import org.springframework.stereotype.Service;

import com.axel.pruebatecnica.api.entity.BookingEntity;
import com.axel.pruebatecnica.api.service.interfaces.IBookingService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookingService implements IBookingService {@Override
	
	public BookingEntity create(BookingEntity booking, int idEvent, int idClient, String seatType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BookingEntity> bookings() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(int idBooking) {
		// TODO Auto-generated method stub
		
	}
//    private final IBookingRepository bookingRepository;
//    private final IEventRepository eventRepository;
//    private final IUserRepository userRepository;
//
//    //crear reserva
//    @Override
//    @Transactional
//    public BookingEntity create(BookingEntity booking, int idEvent, int idClient, String seatTypeString) {
//
//        EventEntity event = eventRepository.findById(idEvent)
//                .orElseThrow(() -> new RuntimeException("Evento no encontrado: " + idEvent));
//
//        UserEntity user = userRepository.findById(idClient)
//                .orElseThrow(() -> new RuntimeException("Usuario no encontrado: " + idClient));
//
//        //setea freepass si 
//        boolean hasFreePass = (user.getBookings().size()+1)%5==0;
//        
//        if(hasFreePass) {
//        	user.setFreePass(true);
//        }else {
//        	user.setFreePass(false);
//        }
//        
//        SeatTypeEnum seatType = parseSeatType(seatTypeString);
//
//        SeatCapacityEntity seatCapacity = findSeatCapacity(event, seatType);
//
//        seatCapacity.setCapacity(seatCapacity.getCapacity() - 1);
//
//        booking.setEvent(event);
//        booking.setUser(user);
//        
//        if(hasFreePass){
//        	booking.setPrice(0);
//        }else {
//        	booking.setPrice(seatType.getValor());
//        }
//        
//        //actualizar usuario por freepass
//        userRepository.save(user);
//        
//        //actualiza el evento
//        eventRepository.save(event);
//        
//        return bookingRepository.save(booking);
//    }
//
//    //todasd las reservas
//    @Override
//    public List<BookingEntity> bookings() {
//
//        return bookingRepository.findAll();
//    }
//
//    //reservas por usuario
//    public List<BookingEntity> myBookings(int idUser) {
//
//        List<BookingEntity> bookings = new ArrayList<>();
//
//        for (BookingEntity b : bookingRepository.findAll()) {
//            if (b.getUser().getIdUser() == idUser) {
//                bookings.add(b);
//            }
//        }
//
//        return bookings;
//    }
//
//    //eliminr
//    @Override
//    public void delete(int idBooking) {
//
//        bookingRepository.deleteById(idBooking);
//
//    }
//    
//    //metodos para uso local del creart
//    private SeatTypeEnum parseSeatType(String seat) {
//        try {
//            return SeatTypeEnum.valueOf(seat.toUpperCase());
//        } catch (IllegalArgumentException ex) {
//            throw new RuntimeException("Tipo de asiento inválido: " + seat);
//        }
//    }
//
//    private SeatCapacityEntity findSeatCapacity(EventEntity event, SeatTypeEnum seatType) {
//        return event.getSeatCapacity()
//                .stream()
//                .filter(seatCap -> seatCap.getSeatType() == seatType)
//                .findFirst()
//                .orElseThrow(() -> new RuntimeException("El evento no tiene capacidad para " + seatType));
//    }

}
