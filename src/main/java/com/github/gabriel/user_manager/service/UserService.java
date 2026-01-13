package com.github.gabriel.user_manager.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.gabriel.user_manager.entity.User;
import com.github.gabriel.user_manager.exception.UserNotFoundException;
import com.github.gabriel.user_manager.repository.UserRepository;

@Service
public class UserService {

	private final UserRepository repository;
	
	public UserService(UserRepository repository) {
		this.repository = repository;
	}
	
	public List<User> findAll() {
		return repository.findAll();
	}
	
	public User findById(Long id) {
		return repository.findById(id).orElseThrow(() -> new UserNotFoundException(
				"Usuário com ID "+id+" não encontrado."));
	}
	
	public User Insert(User obj) {
		return repository.save(obj);
	}
	
	public void deleteById(Long id) {
		repository.deleteById(id);
	}
	
	public User update(Long id, User obj) {
		User userUpdate = repository.findById(id).orElseThrow(() -> new UserNotFoundException(
				"Usuário com ID "+id+" não encontrado."));
		
		userUpdate.setName(obj.getName());
		userUpdate.setEmail(obj.getEmail());
		
		return repository.save(userUpdate);
	}
}
