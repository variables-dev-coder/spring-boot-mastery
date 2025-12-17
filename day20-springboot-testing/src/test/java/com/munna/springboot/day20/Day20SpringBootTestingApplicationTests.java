package com.munna.springboot.day20;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.munna.springboot.day20.Service.UserService;

@SpringBootTest
class Day20SpringBootTestingApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {
        assertNotNull(userService);
    }
}
