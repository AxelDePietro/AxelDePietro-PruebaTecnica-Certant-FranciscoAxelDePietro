package com.axel.pruebatecnica.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.axel.pruebatecnica.api.entity.envents.EventConferenceEntity;

public interface IConferenceRepository extends JpaRepository<EventConferenceEntity , Integer>{

}
