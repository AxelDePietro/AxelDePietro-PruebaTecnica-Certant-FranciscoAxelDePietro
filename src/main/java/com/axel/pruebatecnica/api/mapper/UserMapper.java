package com.axel.pruebatecnica.api.mapper;

import com.axel.pruebatecnica.api.dto.user.UserCreateDTO;
import com.axel.pruebatecnica.api.dto.user.UserResponseDTO;
import com.axel.pruebatecnica.api.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserEntity toEntity (UserCreateDTO dto);

    UserResponseDTO toDTO (UserEntity entity);

}
