package com.axel.pruebatecnica.api.service.implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Service;

import com.axel.pruebatecnica.api.entity.EventEntity;
import com.axel.pruebatecnica.api.repository.IEventRepository;
import com.axel.pruebatecnica.api.service.IEventService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventService implements IEventService {

    private final IEventRepository eventRepository;

    //crear evento
    @Override
    public EventEntity create(EventEntity e) {

        Objects.requireNonNull(e, "error al crear evento");

        return eventRepository.save(e);
    }

    //encontrar por id, booking controller
    @Override
    public EventEntity findById(int idEvent) {
        return eventRepository.findById(idEvent).orElseThrow(() -> new RuntimeException("Evento no encontrado: " + idEvent));
    }

    //lista de eventos
    @Override
    public List<EventEntity> events() {

        return new ArrayList<>(eventRepository.findAll());
    }

    //eliminar evento
    @Override
    public void delete(int idEvent) {

        eventRepository.deleteById(idEvent);

    }

}
