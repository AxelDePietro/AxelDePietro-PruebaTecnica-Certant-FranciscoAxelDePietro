package com.axel.pruebatecnica.api.service.implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.axel.pruebatecnica.api.entity.RoleEntity;
import com.axel.pruebatecnica.api.entity.UserEntity;
import com.axel.pruebatecnica.api.repository.IRoleRepository;
import com.axel.pruebatecnica.api.repository.IUserRepository;
import com.axel.pruebatecnica.api.service.IUserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public UserEntity save(UserEntity user) {

        // rol agergado por defecto
        Optional<RoleEntity> optionalRoleUser = roleRepository.findByName("ROLE_USER");
        // se agrega elrol por defecto a al la lista de roles que se setteara a el
        // usuario
        List<RoleEntity> roles = new ArrayList<>();

        // optionalRoleUser.ifPresent(role->roles.add(role));
        // agrega el rol que posee el usuario
        optionalRoleUser.ifPresent(roles::add);

        if (user.isAdmin()) {
            Optional<RoleEntity> optionalRoleAdmin = roleRepository.findByName("ROLE_ADMIN");
            // agrega admin si es que la bandera esta activa
            optionalRoleAdmin.ifPresent(roles::add);
        }

        // se setea al lista de roles en la lista del usuario
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado o nombre invalido"));
    }

}
