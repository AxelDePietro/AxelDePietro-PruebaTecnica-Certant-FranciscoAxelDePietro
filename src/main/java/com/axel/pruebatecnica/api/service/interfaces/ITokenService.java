package com.axel.pruebatecnica.api.service.interfaces;

import org.springframework.security.core.Authentication;

public interface ITokenService {

    String generateToken(Authentication authentication);

}
