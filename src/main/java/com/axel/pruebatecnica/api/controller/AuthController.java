package com.axel.pruebatecnica.api.controller;

import com.axel.pruebatecnica.api.dto.auth.LoginCreateDTO;
import com.axel.pruebatecnica.api.service.implementations.AuthService;
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

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginCreateDTO loginCreateDTO) {
        return ResponseEntity.status(201).body(authService.login(loginCreateDTO).getToken());
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register() {
        // TODO: Implementar registro
        return ResponseEntity.status(201).body(null);
    }
}
