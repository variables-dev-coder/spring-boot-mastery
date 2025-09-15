package com.munna.springboot.day4.service;

import org.springframework.stereotype.Service;

@Service("fancyService")
public class FancyGreetingService extends GreetingService {
	
	@Override
	public String greet() {
		return "Fancy Greeting from @Qualifier Bean!";
	}

}
