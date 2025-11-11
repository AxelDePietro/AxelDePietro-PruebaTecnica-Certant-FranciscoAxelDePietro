package com.axel.pruebatecnica.api.service.interfaces;

import java.util.List;

import com.axel.pruebatecnica.api.entity.BookingEntity;

public interface IBookingService {

    public BookingEntity create(BookingEntity booking, int idEvent, int idClient, String seatType);

    public List<BookingEntity> bookings();

    public void delete(int idBooking);

}
