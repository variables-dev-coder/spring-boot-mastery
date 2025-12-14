package com.munna.springboot.day19.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class TestController {
	
	@GetMapping("/test")
    public String test() {
        return "Day 19 - DevTools Hot Reload Working!";
    }

}
