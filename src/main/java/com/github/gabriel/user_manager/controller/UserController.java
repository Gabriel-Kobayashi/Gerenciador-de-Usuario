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
	public ResponseEntity<UserDto> findById(@PathVariable Long id) {
		User obj = service.findById(id);
		return ResponseEntity.ok().body(new UserDto(obj));
	}
	
	@PostMapping
	public ResponseEntity<UserDto> insertUser(@RequestBody UserCreateDto objDto) {
		User obj = service.insertDto(objDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(new UserDto(obj));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserUpdateDto objDto) {
		User obj = service.updateDto(id, objDto);
		return ResponseEntity.ok(new UserDto(obj));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
