package com.axel.pruebatecnica.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.axel.pruebatecnica.api.entity.UserEntity;

@Repository
public interface IUserRepository extends JpaRepository<UserEntity, Integer> {

	Optional<UserEntity> findByUsername(String username);

}
