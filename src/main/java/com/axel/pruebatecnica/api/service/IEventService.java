package com.axel.pruebatecnica.api.service;

import java.util.List;

import com.axel.pruebatecnica.api.entity.EventEntity;

public interface IEventService {

    EventEntity create(EventEntity event);

    EventEntity findById(int idEvent);

    List<EventEntity> events();

    void delete(int idEvent);

}
