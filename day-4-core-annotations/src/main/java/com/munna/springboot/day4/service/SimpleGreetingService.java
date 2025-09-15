package com.munna.springboot.day4.service;

import org.springframework.stereotype.Component;

@Component
public class SimpleGreetingService {
	public String greet() {
		return "Hello from @Component Bean!";
	}

}
