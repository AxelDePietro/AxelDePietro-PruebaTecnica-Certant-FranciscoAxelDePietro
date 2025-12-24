package com.axel.pruebatecnica.api.mapper;

import com.axel.pruebatecnica.api.dto.booking.BookingCreateDTO;
import com.axel.pruebatecnica.api.entity.BookingEntity;
import org.mapstruct.Mapper;

import javax.swing.text.html.parser.Entity;

@Mapper(componentModel = "spring")
public interface BookingMapper {

    BookingMapper toEntity (BookingCreateDTO dto);

    BookingMapper toDTO (BookingEntity entity);

}
