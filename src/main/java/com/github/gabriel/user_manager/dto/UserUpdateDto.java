package com.github.gabriel.user_manager.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserUpdateDto(
		
		@NotBlank(message = "Nome não pode ser vazio")
		String name,
		
		@NotBlank(message = "Email não pode ser vazio")
		@Email(message = "Email inválido")
		String email
		
		) 
{}
