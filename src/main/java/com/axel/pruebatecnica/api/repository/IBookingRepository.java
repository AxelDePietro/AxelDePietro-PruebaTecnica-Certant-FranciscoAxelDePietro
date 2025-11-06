package com.axel.pruebatecnica.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.axel.pruebatecnica.api.entity.BookingEntity;

@Repository
public interface IBookingRepository extends JpaRepository<BookingEntity, Integer> {

}
