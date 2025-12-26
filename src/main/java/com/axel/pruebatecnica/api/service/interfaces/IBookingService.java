package com.axel.pruebatecnica.api.service.interfaces;

import java.util.List;

import com.axel.pruebatecnica.api.dto.booking.BookingCreateDTO;
import com.axel.pruebatecnica.api.dto.booking.BookingResponseDTO;
import com.axel.pruebatecnica.api.entity.BookingEntity;

public interface IBookingService {

	public BookingEntity createBooking(BookingCreateDTO booking, int idEvent, int idClient)
			throws Exception;

	public List<BookingResponseDTO> allBookings();

	public void delete(int idBooking);

}
