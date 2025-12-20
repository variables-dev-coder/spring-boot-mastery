package com.munna.springboot.day22.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.munna.springboot.day22.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByUsername(String username);

}


/*

Why?
	Fetch user during login
	Spring Security needs username lookup


*/