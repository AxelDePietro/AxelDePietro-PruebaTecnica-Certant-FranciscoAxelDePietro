package com.axel.pruebatecnica.api.service.implementations;

import java.util.ArrayList;
import java.util.List;

import com.axel.pruebatecnica.api.dto.booking.BookingCreateDTO;
import com.axel.pruebatecnica.api.dto.booking.BookingResponseDTO;
import com.axel.pruebatecnica.api.exceptions.NoEncontrado;
import com.axel.pruebatecnica.api.mapper.BookingMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.axel.pruebatecnica.api.entity.BookingEntity;
import com.axel.pruebatecnica.api.entity.UserEntity;
import com.axel.pruebatecnica.api.entity.enums.SeatTypeEnum;
import com.axel.pruebatecnica.api.entity.envents.EventConcertEntity;
import com.axel.pruebatecnica.api.entity.envents.EventConferenceEntity;
import com.axel.pruebatecnica.api.entity.envents.EventEntity;
import com.axel.pruebatecnica.api.entity.envents.EventTheaterEntity;
import com.axel.pruebatecnica.api.exceptions.ListaVacia;
import com.axel.pruebatecnica.api.exceptions.SinReservas;
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
    private final BookingMapper bookingMapper;

    @Override
	@Transactional
	public BookingEntity createBooking(BookingCreateDTO booking, int idEvent, int idUser)
			throws Exception {

        /// seteo de variables utiles para el proceso
		EventEntity event = eventRepository.findById(idEvent)
				.orElseThrow(() -> new RuntimeException("el evento no se encontro"));

		UserEntity user = userRepository.findById(idUser)
				.orElseThrow(() -> new RuntimeException("el usuario no se encontro"));
		;
		
		String seatType = booking.getSeatType();

        BookingEntity bookingEntity = bookingMapper.toEntity(booking);
		
		/// user atributes (entrada gratis)
		boolean hasFreePass = (user.getBookings().size() + 1) % 5 == 0;

		if (hasFreePass) {
			user.setFreePass(true);
		}

		/// booking atributes
		/// si alguna de esta no se encuentra falla antes{
		// set evento
        bookingEntity.setEvent(event);

		// set usuario
        bookingEntity.setUser(user);
		/// }

		// precio si usuario tiene entrada gratis o no (precio normal)
		if (hasFreePass) {
            bookingEntity.setPrice(0);
		} else {
            bookingEntity.setPrice(SeatTypeEnum.valueOf(seatType).getValor());
		}

		// set enum tipo de asiento, metodo privado
        bookingEntity.setSeatType(SeatTypeEnum.valueOf(seatType));

		/// event atributes

		seatsCount(event, seatType);

		// persistencia de datos
		eventRepository.save(event);

		userRepository.save(user);

		return bookingRepository.save(bookingEntity);
	}

	@Override
	@Transactional(readOnly = true)
	public List<BookingResponseDTO> allBookings() {

		List<BookingResponseDTO> bookings = new ArrayList<>();

        bookings = bookingRepository.findAll().stream().map(bookingMapper::toDTO).toList();

		if (bookings.isEmpty()) {
			throw new SinReservas();
		}

		return bookings;

	}

	@Override
	@Transactional
	public void delete(int idBooking) {

        try {
            bookingRepository.findById(idBooking);
        }catch (Exception e){
            throw new NoEncontrado(idBooking);
        }

        bookingRepository.deleteById(idBooking);

	}

	// reservas por usuario
	@Transactional(readOnly = true)
	public List<BookingResponseDTO> myBookings(int idUser) {

		List<BookingEntity> myBookings = bookingRepository.findAll();

        java.util.List<BookingResponseDTO> myBookingsDTO =
                myBookings.stream()
                        .filter(reserva -> reserva.getUser().getIdUser() == idUser)
                        .map(bookingMapper::toDTO)
                        .toList();

		if (myBookingsDTO.isEmpty()) {
			throw new SinReservas();
		}

		return myBookingsDTO;
	}

//	metodos para uso local del crear
	// resta asientos a la entidad evento que este ligada a la reserva
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
				((EventConcertEntity) event).setPalcoCant(aux - 1);

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
