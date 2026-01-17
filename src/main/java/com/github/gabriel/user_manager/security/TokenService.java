package com.github.gabriel.user_manager.security;



import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;


@Service
public class TokenService {

	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${jwt.expiration}")
	private long expiration;
	
	public String generateToken(UserDetails user) {
		Algorithm algorithm = Algorithm.HMAC256(secret);
		
		return JWT.create()
				.withSubject(user.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() + expiration))
				.sign(algorithm);
	}
	
	public String getSubject(String token) {
		Algorithm algorithm = Algorithm.HMAC256(secret);
		
		return JWT.require(algorithm)
				.build()
				.verify(token)
				.getSubject();
	}
	
}
