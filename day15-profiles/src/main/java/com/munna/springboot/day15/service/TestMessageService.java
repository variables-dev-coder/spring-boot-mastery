package com.munna.springboot.day15.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("test")
public class TestMessageService implements MessageService {
	
	@Override
    public String getMessage() {
        return "TEST SERVICE ACTIVE";
    }

}
