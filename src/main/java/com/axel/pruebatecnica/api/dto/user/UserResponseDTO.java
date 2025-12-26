package com.axel.pruebatecnica.api.dto.user;

import lombok.Data;

@Data
public class UserResponseDTO {

    private int idUser;

    private String username;

    private boolean freePass;

}
