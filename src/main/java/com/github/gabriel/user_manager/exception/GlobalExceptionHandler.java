package com.github.gabriel.user_manager.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Object> handleUserNotFound(UserNotFoundException e) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("status", HttpStatus.NOT_FOUND.value());
		body.put("error", "Usuário não encontrado");
		body.put("message", e.getMessage());
		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleGenericException(Exception e) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", LocalDateTime.now());
		body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
		body.put("error", "Erro interno do servidor");
		body.put("message", e.getMessage());
		return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException e) {
		Map<String, Object> body = new LinkedHashMap<>();
		Map<String, String> fieldErrors = new HashMap<>();
		
		e.getBindingResult()
	     .getFieldErrors()
	     .forEach(error -> fieldErrors.put(error.getField(), error.getDefaultMessage()));
	     
		body.put("timestamp", LocalDateTime.now());
		body.put("status", HttpStatus.BAD_REQUEST);
		body.put("error", "Erro de validação");
		body.put("message", "Campos inválidos");
		body.put("fields", fieldErrors);
		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}
}
