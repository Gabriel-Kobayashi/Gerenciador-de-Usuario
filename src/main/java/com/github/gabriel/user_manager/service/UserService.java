package com.github.gabriel.user_manager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.github.gabriel.user_manager.dto.LoginDto;
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
		return repository.findById(id).orElseThrow(() -> new UserNotFoundException(
				"Usuário com ID "+id+" não encontrado."));
	}
	
	public void deleteById(Long id) {
		if (!repository.existsById(id)) {
			throw new UserNotFoundException("Usuário com ID "+id+" não encontrado.");
		}
		repository.deleteById(id);
	}
	
	public User createDto(UserCreateDto objDto) {
		User obj = new User();
		obj.setName(objDto.name());
		obj.setEmail(objDto.email());
		obj.setPassword(encoder.encode(objDto.password()));
		
		return repository.save(obj);
	}
	
	public User updateDto(Long id, UserUpdateDto objDto) {
		User obj = repository.findById(id).orElseThrow(() -> new UserNotFoundException(
				"Usuário com ID "+id+" não encontrado."));
		
		obj.setName(objDto.name());
		obj.setEmail(objDto.email());
		
		return repository.save(obj);
	}
	
	public User authenticate(LoginDto loginDto) {
		User obj = repository.findByEmail(loginDto.email())
				.orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));
		
		if (!encoder.matches(loginDto.password(), obj.getPassword())) {
			throw new UserNotFoundException("Senha incorreta");
		}
		return obj;
	}
	
	public User findByEmail(String email) {
		return repository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("Usuário com email "+email+" não encontrado."));
	}
	
	public User findByName(String name) {
		return repository.findByName(name).orElseThrow(() -> new UserNotFoundException("Usuário com nome "+name+" não encontrado."));
	}
}
