package com.munna.springboot.day26.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.munna.springboot.day26.entity.User;


public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByUsername(String username);

}
