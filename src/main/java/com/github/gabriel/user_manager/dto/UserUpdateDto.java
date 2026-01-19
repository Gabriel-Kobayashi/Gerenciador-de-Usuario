package com.github.gabriel.user_manager.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserUpdateDto(
		
		@NotBlank(message = "Nome não pode ser vazio")
		String name,
		
		@NotBlank(message = "Email não pode ser vazio")
		@Email(message = "Email inválido")
		String email,
		
		@NotBlank(message = "Senha é obrigatório")
		@Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
		String password
		
		) 
{}
