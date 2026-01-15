package com.github.gabriel.user_manager.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.github.gabriel.user_manager.entity.User;
import com.github.gabriel.user_manager.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		userRepository.deleteAll();
		
		User u1 = new User(null, "João Victor", "joao@gmail.com",null);
		User u2 = new User(null, "Maria Clara", "maria@gmail.com", null);
		User u3 = new User(null, "Carlos Alberto", "carlos@gmail.com", null);
		
		userRepository.saveAll(Arrays.asList(u1, u2, u3));
	}

}
