package com.axel.pruebatecnica.api.service.interfaces;

import java.util.List;

import com.axel.pruebatecnica.api.dto.user.UserCreateDTO;
import com.axel.pruebatecnica.api.dto.user.UserResponseDTO;
import org.springframework.stereotype.Service;

import com.axel.pruebatecnica.api.entity.UserEntity;

@Service
public interface IUserService {

	List<UserResponseDTO> findAll();

	UserEntity save(UserCreateDTO useDTO);
}
