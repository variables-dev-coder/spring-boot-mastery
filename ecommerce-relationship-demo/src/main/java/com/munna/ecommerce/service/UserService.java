package com.munna.ecommerce.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.munna.ecommerce.entity.User;
import com.munna.ecommerce.repository.UserRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class UserService {
	
	private final UserRepository userRepository;
	
	public User createUser(User user) {
		return userRepository.save(user);
	}
	
	public List<User> getAllUser() {
		return userRepository.findAll();
	}
	
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}

}
