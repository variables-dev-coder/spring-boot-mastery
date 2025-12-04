package com.munna.springboot.day15.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("prod")
public class ProdMessageService implements MessageService {
	
	@Override
    public String getMessage() {
        return "PRODUCTION SERVICE ACTIVE";
    }

}
