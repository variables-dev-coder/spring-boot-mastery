package com.munna.springboot.day4.clean.controller;

import com.munna.springboot.day4.clean.service.GreetingService;
import com.munna.springboot.day4.clean.repository.GreetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    // Default service injected (because of @Primary)
    @Autowired
    private GreetingService defaultGreetingService;

    // Explicitly use Qualifier
    @Autowired
    @Qualifier("fancyService")
    private GreetingService fancyGreetingService;

    @Autowired
    private GreetingRepository greetingRepository;

    @GetMapping("/hello")
    public String sayHello() {
        return defaultGreetingService.greet();
    }

    @GetMapping("/fancy")
    public String sayFancyHello() {
        return fancyGreetingService.greet();
    }

    @GetMapping("/repo")
    public String repoMessage() {
        return greetingRepository.getMessage();
    }
}

