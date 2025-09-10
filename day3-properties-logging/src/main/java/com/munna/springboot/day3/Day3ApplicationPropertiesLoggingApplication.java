package com.munna.springboot.day3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Day3ApplicationPropertiesLoggingApplication {

	public static void main(String[] args) {
		SpringApplication.run(Day3ApplicationPropertiesLoggingApplication.class, args);
	}

}


/*
Day 3: Application Properties & Logging in Spring Boot

Q1: What is the difference between application.properties and application.yml in Spring Boot?

Answer:

application.properties → uses simple key=value format.

server.port=8081
spring.application.name=myapp


application.yml → uses YAML indentation for hierarchical configs (cleaner for complex settings).


server:
  port: 8081
spring:
  application:
    name: myapp

oth work the same way; choose YAML when configs are nested.




Q2: How can you change the default server port in Spring Boot?

Answer:
By setting property in application.properties:
server.port=8082

or in application.yml:
server:
  port: 8082


Default port is 8080.


Q3: How do you set the application name in Spring Boot? Why is it useful?

Answer:

Property:

spring.application.name=MyApp


It helps identify the app in logs, monitoring tools, and when working in microservices.



Q4: What are the logging levels in Spring Boot?

Answer:
Spring Boot (via Logback) supports:

TRACE < DEBUG < INFO < WARN < ERROR


TRACE → detailed flow, rarely used.

DEBUG → developer info.

INFO → general application messages.

WARN → potential problems.

ERROR → failures.


Q5: How do you configure logging levels in Spring Boot?

Answer:
In application.properties:

logging.level.root=INFO
logging.level.com.munna.springboot.day3=DEBUG


root → default logging level for all packages.

Specific package/class can be set separately.



Q6: What is SLF4J and how is it used in Spring Boot?

Answer:

SLF4J (Simple Logging Facade for Java) → logging API that allows you to plug in 
different logging frameworks (Logback, Log4j, JUL).

In Spring Boot, default backend is Logback.

Example usage:

private static final Logger logger = LoggerFactory.getLogger(MyClass.class);

logger.info("Info message");
logger.debug("Debug message");




Q7: What is Logback and why does Spring Boot use it by default?

Answer:

Logback is a powerful, flexible, and fast logging framework.

Provides advanced configuration (logback-spring.xml) and rolling file support.

Chosen as Spring Boot’s default because it’s lightweight, production-ready, 
and integrates well with SLF4J.



Q8: How do you create a custom logging format in Spring Boot?

Answer:
By creating logback-spring.xml in resources:

<configuration>
  <appender name="CONSOLE" class="ch.qos.logback.core.ConsoleAppender">
    <encoder>
      <pattern>%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n</pattern>
    </encoder>
  </appender>
  <root level="INFO">
    <appender-ref ref="CONSOLE"/>
  </root>
</configuration>




Q9: If you set logging.level.root=ERROR, what will happen to INFO logs?

Answer:

Only ERROR level logs (and above) will be shown.

INFO, DEBUG, TRACE, WARN logs will be hidden.




Q10: How can you log only for your package in DEBUG but keep other packages in INFO?

Answer:

logging.level.root=INFO
logging.level.com.munna.springboot.day3=DEBUG





*/




