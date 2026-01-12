package com.github.gabriel.user_manager.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.gabriel.user_manager.entity.User;
import com.github.gabriel.user_manager.service.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {

	private final UserService service;
	
	public UserController(UserService service) {
		this.service = service;
	}
	
	@GetMapping
	public ResponseEntity<List<User>> findAll() {
		List<User> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
}
