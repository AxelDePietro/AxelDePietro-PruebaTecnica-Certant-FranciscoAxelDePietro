package com.axel.pruebatecnica.api.controller;

import com.axel.pruebatecnica.api.dto.user.UserResponseDTO;
import com.axel.pruebatecnica.api.service.implementations.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/list")
    public ResponseEntity<List<UserResponseDTO>> userList(){
        List<UserResponseDTO> userList = userService.findAll();
        return ResponseEntity.ok(userList);
    }

}
