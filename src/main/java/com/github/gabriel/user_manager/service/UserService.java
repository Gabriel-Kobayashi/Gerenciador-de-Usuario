package com.github.gabriel.user_manager.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.gabriel.user_manager.entity.User;
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
}
