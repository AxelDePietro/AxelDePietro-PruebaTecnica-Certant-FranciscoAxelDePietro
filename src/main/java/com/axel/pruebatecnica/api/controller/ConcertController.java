package com.axel.pruebatecnica.api.controller;

import com.axel.pruebatecnica.api.dto.event.concert.ConcertCreateDTO;
import com.axel.pruebatecnica.api.dto.event.concert.ConcertResponseDTO;
import com.axel.pruebatecnica.api.mapper.ConcertMapper;
import com.axel.pruebatecnica.api.service.implementations.ConcertService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/concert")
public class ConcertController {

    private final ConcertService concertService;
    private final ConcertMapper concertMapper;

    @GetMapping("/listadoConciertos")
    public ResponseEntity<List<ConcertResponseDTO>> listaConciertos() {
        return ResponseEntity.status(200).body(concertService.allConcerts());
    }

    @PostMapping("crearConcierto")
    public ResponseEntity<ConcertResponseDTO> crearConcierto(@RequestBody ConcertCreateDTO dtoConcert) {
        return ResponseEntity.status(201).body(concertMapper.toDTO(concertService.createConcert(dtoConcert)));
    }
}
