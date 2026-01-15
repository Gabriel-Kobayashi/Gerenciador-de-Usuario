package com.github.gabriel.user_manager.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginDto(
		
		@NotBlank(message = "Email é obrigatório")
		@Email
		String email,
		
		@NotBlank(message = "Senha é obrigatório")
		@Size(min = 6, message = "Senha com mínimo 6 caracteres")
		String password
		
		) 
{}
