package com.axel.pruebatecnica.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.axel.pruebatecnica.api.entity.envents.EventTheaterEntity;

public interface ITheaterRepository extends JpaRepository<EventTheaterEntity , Integer>{

}
