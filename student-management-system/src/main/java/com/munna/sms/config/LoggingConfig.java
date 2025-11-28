package com.munna.sms.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;


@Configuration
public class LoggingConfig {
	
	public LoggingConfig() {
        Logger logger = LoggerFactory.getLogger(LoggingConfig.class);
        logger.info("LoggingConfig initialized successfully.");
    }

}
