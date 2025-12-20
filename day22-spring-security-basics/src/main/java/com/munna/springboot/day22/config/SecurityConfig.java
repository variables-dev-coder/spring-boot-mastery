package com.munna.springboot.day22.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;



@Configuration
public class SecurityConfig {
	
	@Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

	    http
	        .csrf(csrf -> csrf
	            .ignoringRequestMatchers("/h2-console/**")
	            .disable()
	        )
	        .authorizeHttpRequests(auth -> auth
	            .requestMatchers("/h2-console/**").permitAll()
	            .requestMatchers("/public/**").permitAll()
	            .requestMatchers("/admin/**").hasRole("ADMIN")
	            .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
	            .anyRequest().authenticated()
	        )
	        .headers(headers -> headers
	            .frameOptions(frame -> frame.disable())
	        )
	        .formLogin(form -> form
	            .defaultSuccessUrl("/public/welcome", true)
	        );

	    return http.build();
	}


    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

}


/*
 
 Explanation (Interview GOLD)
	hasRole("ADMIN") → checks ROLE_ADMIN
	formLogin() → enables login page
	BCryptPasswordEncoder → password encryption


WHY This Works (Interview Explanation)

| Setting                    | Reason                      |
| -------------------------- | --------------------------- |
| `permitAll()`              | Allows access without login |
| `csrf.ignore()`            | H2 uses POST forms          |
| `frameOptions().disable()` | H2 loads in iframe          |
| `h2-console/**`            | Full H2 console access      |



 */
