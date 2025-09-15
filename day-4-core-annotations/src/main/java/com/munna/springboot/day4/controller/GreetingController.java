package com.munna.springboot.day4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.munna.springboot.day4.repository.GreetingRepository;
import com.munna.springboot.day4.service.GreetingService;

@Controller
public class GreetingController {
	
	@Autowired
    private GreetingService greetingService; // @Primary injected here

    @Autowired
    @Qualifier("fancyService")
    private GreetingService fancyGreetingService; // Explicit fancy service

    @Autowired
    private GreetingRepository greetingRepository; // Repository injected

    @GetMapping("/hello")
    @ResponseBody
    public String sayHello() {
        return greetingService.greet();
    }

    @GetMapping("/fancy")
    @ResponseBody
    public String sayFancyHello() {
        return fancyGreetingService.greet();
    }

    @GetMapping("/repo")
    @ResponseBody
    public String repoMessage() {
        return greetingRepository.getMessage();
    }
}
