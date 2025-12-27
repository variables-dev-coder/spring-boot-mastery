package com.munna.springboot.day24.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.munna.springboot.day24.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByUsername(String username);

}
