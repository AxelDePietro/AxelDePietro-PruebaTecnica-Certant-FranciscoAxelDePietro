package com.axel.pruebatecnica.api.service.interfaces;

import java.util.List;

import com.axel.pruebatecnica.api.dto.event.theater.TheaterCreateDTO;
import com.axel.pruebatecnica.api.dto.event.theater.TheaterResponseDTO;
import com.axel.pruebatecnica.api.entity.envents.EventTheaterEntity;

public interface ITheaterService {

	public EventTheaterEntity createTheater(TheaterCreateDTO theaterDTO);

	public List<TheaterResponseDTO> allTheaters();

	public void delete(int idTheater);

}
