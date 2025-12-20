package com.munna.springboot.day22.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.munna.springboot.day22.entity.User;
import com.munna.springboot.day22.repository.UserRepository;

@Configuration
public class DataLoader {
	
	@Bean
    CommandLineRunner loadUsers(
            UserRepository userRepository,
            BCryptPasswordEncoder encoder) {

        return args -> {

            User user = new User(
                    null,
                    "munna",
                    encoder.encode("user123"),
                    "ROLE_USER"
            );

            User admin = new User(
                    null,
                    "admin",
                    encoder.encode("admin123"),
                    "ROLE_ADMIN"
            );

            userRepository.save(user);
            userRepository.save(admin);
        };
    }

}

/*
 Why this is important?
	Passwords are encrypted
	Users are auto-created on startup
	Real production-style setup


 */
