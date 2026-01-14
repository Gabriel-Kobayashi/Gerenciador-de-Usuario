package com.github.gabriel.user_manager.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.gabriel.user_manager.dto.UserCreateDto;
import com.github.gabriel.user_manager.dto.UserDto;
import com.github.gabriel.user_manager.dto.UserUpdateDto;
import com.github.gabriel.user_manager.entity.User;
import com.github.gabriel.user_manager.exception.UserNotFoundException;
import com.github.gabriel.user_manager.service.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {

	private final UserService service;
	
	public UserController(UserService service) {
		this.service = service;
	}
	
	@GetMapping
	public ResponseEntity<List<UserDto>> findAll() {
		List<User> list = service.findAll();
		List<UserDto> listDto = list.stream().map(x -> new UserDto(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		try {
			User obj = service.findById(id);
			return ResponseEntity.ok(new UserDto(obj));
		}
		catch (UserNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@PostMapping
	public ResponseEntity<UserDto> insertUser(@RequestBody UserCreateDto objDto) {
		User obj = service.insertDto(objDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(new UserDto(obj));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserUpdateDto objDto) {
		try {
			User obj = service.updateDto(id, objDto);
			return ResponseEntity.ok(new UserDto(obj));
		}
		catch (UserNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		try {
			service.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		catch (UserNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@GetMapping("/email/{email}")
	public ResponseEntity<?> findByEmail(@PathVariable String email) {
		try {
			User obj = service.findByEmail(email);
			return ResponseEntity.ok(new UserDto(obj));
		}
		catch (UserNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@GetMapping("/name/{name}")
	public ResponseEntity<?> findByNome(@PathVariable String name) {
		try {
			User obj = service.findByName(name);
			return ResponseEntity.ok(new UserDto(obj));
		}
		catch (UserNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
}
