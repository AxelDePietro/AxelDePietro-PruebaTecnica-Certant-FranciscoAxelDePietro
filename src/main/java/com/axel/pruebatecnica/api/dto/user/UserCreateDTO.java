package com.axel.pruebatecnica.api.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Transient;
import lombok.Data;

@Data
public class UserCreateDTO {

    private String username;

    private String password;

}
