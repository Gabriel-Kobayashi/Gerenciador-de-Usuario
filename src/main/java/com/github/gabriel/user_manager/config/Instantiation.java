package com.github.gabriel.user_manager.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.github.gabriel.user_manager.entity.User;
import com.github.gabriel.user_manager.repository.UserRepository;

@Component
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Override
	public void run(String... args) throws Exception {
		
		User u1 = new User(null, "João Victor", "joao@gmail.com", encoder.encode("123456"));
		User u2 = new User(null, "Maria Clara", "maria@gmail.com", encoder.encode("123456"));
		User u3 = new User(null, "Carlos Alberto", "carlos@gmail.com", encoder.encode("123456"));
		
		userRepository.saveAll(Arrays.asList(u1, u2, u3));
	}

}
