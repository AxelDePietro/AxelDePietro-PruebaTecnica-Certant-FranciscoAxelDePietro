package com.axel.pruebatecnica.api.service.interfaces;

import java.util.List;

import com.axel.pruebatecnica.api.dto.user.UserCreateDTO;
import org.springframework.stereotype.Service;

import com.axel.pruebatecnica.api.entity.UserEntity;

@Service
public interface IUserService {

	List<UserEntity> findAll();

	UserEntity save(UserCreateDTO useDTO);
}
