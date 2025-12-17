package com.munna.springboot.day20.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.munna.springboot.day20.Entity.User;
import com.munna.springboot.day20.Repository.UserRepository;
import com.munna.springboot.day20.Service.UserService;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
	
	@Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void saveUserTest() {
        User user = new User();
        user.setName("Munna");
        user.setEmail("munna@gmail.com");

        Mockito.when(userRepository.save(user)).thenReturn(user);

        User savedUser = userService.saveUser(user);

        Assertions.assertEquals("Munna", savedUser.getName());
    }

}
