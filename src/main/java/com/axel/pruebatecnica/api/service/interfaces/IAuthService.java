package com.axel.pruebatecnica.api.service.interfaces;

import com.axel.pruebatecnica.api.dto.auth.LoginCreateDTO;
import com.axel.pruebatecnica.api.dto.auth.LoginResponseDTO;
import com.axel.pruebatecnica.api.dto.user.UserCreateDTO;

public interface IAuthService {
    LoginResponseDTO login( LoginCreateDTO loginCreateDTO);
    void register (UserCreateDTO createDTO);
}
