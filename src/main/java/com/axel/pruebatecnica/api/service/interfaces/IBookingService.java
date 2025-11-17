package com.axel.pruebatecnica.api.service.interfaces;

import java.util.List;

import com.axel.pruebatecnica.api.entity.BookingEntity;

public interface IBookingService {

    public BookingEntity createBooking(BookingEntity booking, int idEvent, int idClient, String seatType) throws Exception;

    public List<BookingEntity> allBookings();

    public void delete(int idBooking);

}
