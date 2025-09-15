package com.munna.springboot.day4.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class DefaultGreetingService extends GreetingService{
	
	@Override
	public String greet() {
		return "Hello from @Primary greeting Service!";
	}

}
