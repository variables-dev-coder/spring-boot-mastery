package com.munna.springboot.day25.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.munna.springboot.day25.entity.Role;
import com.munna.springboot.day25.entity.User;
import com.munna.springboot.day25.repository.UserRepository;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initUsers(UserRepository userRepository,
                                PasswordEncoder passwordEncoder) {

        return args -> {

            if (userRepository.count() == 0) {

                User user = User.builder()
                        .username("user")
                        .password(passwordEncoder.encode("user123"))
                        .role(Role.ROLE_USER)
                        .build();

                User admin = User.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("admin123"))
                        .role(Role.ROLE_ADMIN)
                        .build();

                userRepository.save(user);
                userRepository.save(admin);
            }
        };
    }
}
