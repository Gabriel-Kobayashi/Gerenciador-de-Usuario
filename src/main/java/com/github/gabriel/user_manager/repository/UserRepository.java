package com.github.gabriel.user_manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.gabriel.user_manager.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	
}
