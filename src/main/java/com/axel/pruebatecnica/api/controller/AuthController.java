package com.axel.pruebatecnica.api.controller;

import com.axel.pruebatecnica.api.dto.auth.LoginCreateDTO;
import com.axel.pruebatecnica.api.dto.auth.LoginResponseDTO;
import com.axel.pruebatecnica.api.dto.user.UserCreateDTO;
import com.axel.pruebatecnica.api.entity.UserEntity;
import com.axel.pruebatecnica.api.service.implementations.AuthService;
import com.axel.pruebatecnica.api.service.implementations.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginCreateDTO loginCreateDTO) {
        return ResponseEntity.ok(authService.login(loginCreateDTO));
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody UserCreateDTO createDTO) {
        userService.save(createDTO);
        return ResponseEntity.status(201).body(null);
    }
}
