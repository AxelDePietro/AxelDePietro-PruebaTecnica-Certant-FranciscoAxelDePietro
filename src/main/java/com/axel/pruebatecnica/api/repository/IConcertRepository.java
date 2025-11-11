package com.axel.pruebatecnica.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.axel.pruebatecnica.api.entity.envents.EventConcertEntity;

public interface IConcertRepository extends JpaRepository<EventConcertEntity, Integer>{

}
