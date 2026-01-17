package com.github.gabriel.user_manager.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.gabriel.user_manager.dto.LoginDto;
import com.github.gabriel.user_manager.dto.RegisterUserRequest;
import com.github.gabriel.user_manager.dto.RegisterUserResponse;
import com.github.gabriel.user_manager.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	private UserService service;
	
	private AuthenticationManager authenticationManager;
	
	public AuthController(AuthenticationManager authenticationManager, UserService service) {
		this.authenticationManager = authenticationManager;
		this.service = service;
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {
		UsernamePasswordAuthenticationToken authToken =
				new UsernamePasswordAuthenticationToken(loginDto.email(), loginDto.password());
		
		Authentication authentication = authenticationManager.authenticate(authToken);
		
		return ResponseEntity.ok("Usuário autenticado com sucesso!");
	}
	
	@PostMapping("/register")
	public ResponseEntity<RegisterUserResponse> register(@RequestBody RegisterUserRequest request) {
		RegisterUserResponse response = service.register(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
}
