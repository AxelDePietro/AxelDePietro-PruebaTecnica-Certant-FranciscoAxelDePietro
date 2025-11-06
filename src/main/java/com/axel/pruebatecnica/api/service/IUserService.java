package com.axel.pruebatecnica.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.axel.pruebatecnica.api.entity.UserEntity;

@Service
public interface IUserService {

    List<UserEntity> findAll();

    UserEntity save(UserEntity user);
}
