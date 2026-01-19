package com.github.gabriel.user_manager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.github.gabriel.user_manager.dto.LoginDto;
import com.github.gabriel.user_manager.dto.RegisterUserRequest;
import com.github.gabriel.user_manager.dto.RegisterUserResponse;
import com.github.gabriel.user_manager.dto.UserCreateDto;
import com.github.gabriel.user_manager.dto.UserUpdateDto;
import com.github.gabriel.user_manager.entity.User;
import com.github.gabriel.user_manager.exception.UserNotFoundException;
import com.github.gabriel.user_manager.repository.UserRepository;

@Service
public class UserService {

	private final UserRepository repository;

	public UserService(UserRepository repository) {
		this.repository = repository;
	}

	@Autowired
	private PasswordEncoder encoder;

	public List<User> findAll() {
		return repository.findAll();
	}

	public User findById(Long id) {
		return repository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("Usuário com ID " + id + " não encontrado."));
	}

	public void deleteById(Long id) {
		if (!repository.existsById(id)) {
			throw new UserNotFoundException("Usuário com ID " + id + " não encontrado.");
		}
		repository.deleteById(id);
	}

	public RegisterUserResponse register(RegisterUserRequest request) {
		User obj = new User();
		obj.setName(request.name());
		obj.setEmail(request.email());
		obj.setPassword(encoder.encode(request.password()));

		User savedUser = repository.save(obj);

		return new RegisterUserResponse(savedUser.getName(), savedUser.getEmail());
	}

	public User authenticate(LoginDto loginDto) {
		User obj = repository.findByEmail(loginDto.email())
				.orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));

		if (!encoder.matches(loginDto.password(), obj.getPassword())) {
			throw new UserNotFoundException("Senha incorreta");
		}
		return obj;
	}

	public User updateDto(Long id, UserUpdateDto objDto) {
		User obj = repository.findById(id)
				.orElseThrow(() -> new UserNotFoundException("Usuário com ID " + id + " não encontrado."));

		obj.setName(objDto.name());
		obj.setEmail(objDto.email());

		return repository.save(obj);
	}
	
	public User updateParcial(Long id, UserUpdateDto dto) {
		User user = repository.findById(id).orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));
		
		if (dto.name() != null) {
			user.setName(dto.name());
		}
		if (dto.email() != null) {
			user.setEmail(dto.email());
		}
		if (dto.password() != null) {
			user.setPassword(encoder.encode(dto.password()));
		}
		return repository.save(user);
	}

	public User findByEmailOrName(String email, String name) {

		if (email != null) {
			return repository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));
		}

		if (name != null) {
			return repository.findByName(name).orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));
		}
		throw new UserNotFoundException("Informe email ou nome");
	}
}
