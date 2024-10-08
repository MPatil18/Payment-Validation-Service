package com.cpt.payments.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

import com.cpt.payments.exception.ExceptionHandlerFilter;
import com.cpt.payments.security.HmacFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {        //plugin the hmac filter in the series of spring Filter 
	
	private ExceptionHandlerFilter exceptionHandlerFilter;
	private HmacFilter hmacFilter;
	public SecurityConfiguration(HmacFilter hmacFilter,ExceptionHandlerFilter exceptionHandlerFilter)
	{
		this.exceptionHandlerFilter=exceptionHandlerFilter;
		this.hmacFilter=hmacFilter;
	}
	
	@Bean
	 SecurityFilterChain configure(HttpSecurity http) throws Exception {
		
		http
	    .csrf(csrf -> csrf.disable())
	    .authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated())
	   
	    .addFilterBefore(exceptionHandlerFilter, AuthorizationFilter.class)
	    .addFilterBefore(hmacFilter,AuthorizationFilter.class)			//in series of filters added hmacFilter before authorization filter

		.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		
		return http.build();
	}
}
