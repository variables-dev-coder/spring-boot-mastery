package com.munna.springboot.day20.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.munna.springboot.day20.Entity.User;
import com.munna.springboot.day20.Repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;


	public User saveUser(User user) {
	return userRepository.save(user);
	}


	public User getUser(Long id) {
	return userRepository.findById(id)
	.orElseThrow(() -> new RuntimeException("User not found"));
	}

}
