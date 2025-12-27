package com.munna.springboot.day23.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.munna.springboot.day23.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByUsername(String username);

}
