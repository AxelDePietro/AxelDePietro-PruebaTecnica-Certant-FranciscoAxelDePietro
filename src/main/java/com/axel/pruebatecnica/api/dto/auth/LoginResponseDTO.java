package com.axel.pruebatecnica.api.dto.auth;

import lombok.Data;

@Data
public class LoginResponseDTO {

    private String token;
    private String username;

}
