package com.munna.springboot.day15.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.munna.springboot.day15.service.MessageService;

@RestController
public class ProfileController {
	
	private final MessageService messageService;

    @Value("${app.message}")
    private String message;

    @Value("${app.environment}")
    private String env;

    public ProfileController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/profile")
    public String getProfile() {
        return "Profile: " + env +
               "<br/>Message: " + message +
               "<br/>Service: " + messageService.getMessage();
    }

}
