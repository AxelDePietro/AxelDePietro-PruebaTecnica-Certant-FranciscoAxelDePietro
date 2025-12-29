package com.axel.pruebatecnica.api.service.implementations;

import com.axel.pruebatecnica.api.dto.auth.LoginCreateDTO;
import com.axel.pruebatecnica.api.dto.auth.LoginResponseDTO;
import com.axel.pruebatecnica.api.dto.user.UserCreateDTO;
import com.axel.pruebatecnica.api.service.interfaces.IAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

//security
@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @Override
    public LoginResponseDTO login(LoginCreateDTO loginCreateDTO) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginCreateDTO.getUsername(),
                loginCreateDTO.getPassword());


        Authentication auth = authenticationManager.authenticate(authenticationToken);


        LoginResponseDTO response = new LoginResponseDTO();

        response.setToken(tokenService.generateToken(auth));
        response.setUsername(auth.getName());

        return response;
    }

    @Override
    public void register(UserCreateDTO createDTO) {

    }
}
