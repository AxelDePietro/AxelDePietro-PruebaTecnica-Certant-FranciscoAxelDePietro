package com.axel.pruebatecnica.api.service.implementations;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.axel.pruebatecnica.api.entity.BookingEntity;
import com.axel.pruebatecnica.api.entity.UserEntity;
import com.axel.pruebatecnica.api.entity.enums.SeatTypeEnum;
import com.axel.pruebatecnica.api.entity.envents.EventConcertEntity;
import com.axel.pruebatecnica.api.entity.envents.EventConferenceEntity;
import com.axel.pruebatecnica.api.entity.envents.EventEntity;
import com.axel.pruebatecnica.api.entity.envents.EventTheaterEntity;
import com.axel.pruebatecnica.api.repository.IBookingRepository;
import com.axel.pruebatecnica.api.repository.IEventRepository;
import com.axel.pruebatecnica.api.repository.IUserRepository;
import com.axel.pruebatecnica.api.service.interfaces.IBookingService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookingService implements IBookingService {

	private final IBookingRepository bookingRepository;
	private final IEventRepository eventRepository;
	private final IUserRepository userRepository;

	@Override
	public BookingEntity createBooking(BookingEntity booking, int idEvent, int idClient, String seatType)
			throws Exception {

		EventEntity event = eventRepository.findById(idEvent)
				.orElseThrow(() -> new RuntimeException("el evento no se encontro"));

		UserEntity user = userRepository.findById(idClient)
				.orElseThrow(() -> new RuntimeException("el usuario no se encontro"));
		;

		// user atributes (entrada gratis)
		boolean hasFreePass = (user.getBookings().size() + 1) % 5 == 0;

		if (hasFreePass) {
			user.setFreePass(true);
		} else {
			user.setFreePass(false);
		}

		// booking atributes

		// set evento
		try {
			booking.setEvent(event);
		} catch (Exception e) {
			e.getMessage();
		}

		// set usuario
		try {
			booking.setUser(user);
		} catch (Exception e) {
			e.getMessage();
		}

		// precio si usuario tiene entrada gratis o no (precio normal)
		if (hasFreePass) {
			booking.setPrice(0);
		} else {
			booking.setPrice(SeatTypeEnum.valueOf(seatType).getValor());
		}

		// set enum tipo de asiento, metodo privado
		booking.setSeatType(SeatTypeEnum.valueOf(seatType));

		// event atributes
		seatsCount(event, seatType);

		// persistencia de datos
		eventRepository.save(event);

		userRepository.save(user);

		return bookingRepository.save(booking);
	}

	@Override
	public List<BookingEntity> allBookings() {
		return bookingRepository.findAll();
	}

	@Override
	public void delete(int idBooking) {
		bookingRepository.deleteById(idBooking);
	}

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

//	metodos para uso local del crear

	private void seatsCount(EventEntity event, String seatType) {

		int aux = 0;

		if (event instanceof EventConcertEntity) {
			switch (seatType) {

			case "RECITAL_CAMPO":

				aux = ((EventConcertEntity) event).getCampoCant();
				((EventConcertEntity) event).setCampoCant(aux - 1);

				break;

			case "RECITAL_PLATEA":

				aux = ((EventConcertEntity) event).getPlateaCant();
				((EventConcertEntity) event).setPlateaCant(aux - 1);

				break;

			case "RECITAL_PALCO":

				aux = ((EventConcertEntity) event).getPalcoCant();
				((EventConcertEntity) event).setPlateaCant(aux - 1);

				break;

			default:
				throw new IllegalArgumentException("Unexpected value: " + seatType);
			}
		}

		if (event instanceof EventTheaterEntity) {
			switch (seatType) {

			case "OBRA_ENTRADA_GENERAL":

				aux = ((EventTheaterEntity) event).getGeneralCant();
				((EventTheaterEntity) event).setGeneralCant(aux - 1);

				break;

			case "OBRA_ENTRADA_VIP":

				aux = ((EventTheaterEntity) event).getVipCant();
				((EventTheaterEntity) event).setVipCant(aux - 1);

				break;

			default:
				throw new IllegalArgumentException("Unexpected value: " + seatType);
			}
		}

		if (event instanceof EventConferenceEntity) {
			switch (seatType) {

			case "CHARLA_SIN_MEET":

				aux = ((EventConferenceEntity) event).getCharlaSinMeetCant();
				((EventConferenceEntity) event).setCharlaSinMeetCant(aux - 1);

				break;

			case "CHARLA_CON_MEET":

				aux = ((EventConferenceEntity) event).getCharlaConMeetCant();
				((EventConferenceEntity) event).setCharlaConMeetCant(aux - 1);

				break;

			default:
				throw new IllegalArgumentException("Unexpected value: " + seatType);
			}
		}

	}

}
