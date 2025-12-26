package com.axel.pruebatecnica.api.controller.REST;

import com.axel.pruebatecnica.api.dto.user.UserCreateDTO;
import com.axel.pruebatecnica.api.dto.user.UserResponseDTO;
import com.axel.pruebatecnica.api.entity.UserEntity;
import com.axel.pruebatecnica.api.mapper.UserMapper;
import com.axel.pruebatecnica.api.service.implementations.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;
//    @GetMapping("/lista")
//    List<UserResponseDTO> list(){
//        return userService.findAll();
//    }
//
//    @PostMapping("/register")
//    public ResponseEntity<UserEntity> create (@RequestBody UserCreateDTO user){
//        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
//    }
//
//    @GetMapping("/hola")
//    public ResponseEntity<String> hola() {
//        return ResponseEntity.ok("Hola");
//    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/lista")
    public List<UserResponseDTO> list() {
        return userService.findAll();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody UserCreateDTO user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserCreateDTO user) {
        return create(user);
    }

}
