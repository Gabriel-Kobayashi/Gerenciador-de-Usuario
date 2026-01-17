package com.github.gabriel.user_manager.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterUserResponse(
		
		@NotBlank(message = "Nome é obrigatório")
		String name,
		
		@NotBlank(message = "Email é obrigatório")
		@Email(message = "Email inválido")
		String email
		
		) {

}
