package com.munna.springboot.day4.clean.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class DefaultGreetingService implements GreetingService {
    @Override
    public String greet() {
        return "Hello from @Primary DefaultGreetingService!";
    }
}

