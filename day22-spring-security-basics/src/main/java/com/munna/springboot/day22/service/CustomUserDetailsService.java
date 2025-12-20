package com.munna.springboot.day22.service;

import java.util.Collections;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.munna.springboot.day22.entity.User;
import com.munna.springboot.day22.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	 private final UserRepository userRepository;

	    public CustomUserDetailsService(UserRepository userRepository) {
	        this.userRepository = userRepository;
	    }

	    @Override
	    public UserDetails loadUserByUsername(String username)
	            throws UsernameNotFoundException {

	        User user = userRepository.findByUsername(username)
	                .orElseThrow(() ->
	                        new UsernameNotFoundException("User not found"));

	        return new org.springframework.security.core.userdetails.User(
	                user.getUsername(),
	                user.getPassword(),
	                Collections.singleton(() ->
	                        user.getRole())
	        );
	    }
}


/*
 Why?
	Spring Security calls this during login
	Converts DB user → Security user


 */
