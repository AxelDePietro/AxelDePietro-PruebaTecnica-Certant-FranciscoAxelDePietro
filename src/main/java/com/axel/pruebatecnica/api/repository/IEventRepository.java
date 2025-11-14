package com.axel.pruebatecnica.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.axel.pruebatecnica.api.entity.envents.EventEntity;

public interface IEventRepository extends JpaRepository<EventEntity, Integer>{

}
