package com.yannfigueiredo.petsarea.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	private static final String[] PUBLIC_MATCHERS = { "/**" };
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.headers().frameOptions().disable();
		http.cors().and().csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.authorizeRequests().antMatchers(PUBLIC_MATCHERS).permitAll();
		
		return http.build();
	}
}
