package com.axel.pruebatecnica.api.exceptions;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class Error {

    private int status;
    private String code;
    private String message;
    private String path;
    private LocalDateTime timestamp = LocalDateTime.now();

    public Error( int status, String code, String message, String path) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.path = path;
    }

}
