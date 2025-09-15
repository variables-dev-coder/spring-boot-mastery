package com.munna.springboot.day4.clean.service;

import org.springframework.stereotype.Service;

@Service("fancyService")
public class FancyGreetingService implements GreetingService {
    @Override
    public String greet() {
        return "Fancy Greeting from FancyGreetingService!";
    }
}

