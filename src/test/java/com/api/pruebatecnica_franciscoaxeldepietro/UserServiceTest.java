package com.api.pruebatecnica_franciscoaxeldepietro;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.axel.pruebatecnica.api.entity.RoleEntity;
import com.axel.pruebatecnica.api.entity.UserEntity;
import com.axel.pruebatecnica.api.repository.IRoleRepository;
import com.axel.pruebatecnica.api.repository.IUserRepository;
import com.axel.pruebatecnica.api.service.implementations.UserService;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

	@Mock
	private IUserRepository userRepository;
	@Mock
	private IRoleRepository roleRepository;
	@Mock
	private PasswordEncoder passwordEncoder;

	@InjectMocks
	private UserService service;

	private UserEntity user;
	private RoleEntity role;

	@BeforeEach
	void setUp() {
		user = new UserEntity();
		user.setIdUser(1);
		user.setUsername("usuario");
		user.setPassword("1234");

		role = new RoleEntity();
		role.setIdRole(1);
		role.setName("ROLE_USER");
	}

	@Test
	void crearUsuarioFalla() {
		when(roleRepository.findByName("ROLE_USER")).thenReturn(Optional.empty());
		assertThrows(RuntimeException.class, () -> service.save(user));
	}

	@Test
	void buscarUsuarioCorrecto() {
		when(userRepository.findByUsername("usuario")).thenReturn(Optional.of(user));

		UserEntity result = service.findByUsername("usuario");

		assertNotNull(result);
		assertEquals("usuario", result.getUsername());
	}

	@Test
	void buscarUsuarioFalla() {
		when(userRepository.findByUsername("usuario")).thenReturn(Optional.empty());

		assertThrows(RuntimeException.class, () -> service.findByUsername("usuario"));
	}
}