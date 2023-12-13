package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration {

@Bean
public UserDetailsService userDetailsService() {
	UserDetails user= User
			.builder()
			.username("user")
			.password(passwordEncoder().encode("user"))
			.build();
	
	UserDetails admin= User
			.builder()
			.username("admin")
			.password(passwordEncoder().encode("admin"))
			.build();
	return new InMemoryUserDetailsManager(user,admin);
}
	
public PasswordEncoder passwordEncoder() {
	return new BCryptPasswordEncoder();
}

@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
	http
	.formLogin(form -> form
			.defaultSuccessUrl("/display",true)
			.failureUrl("/login?error=true")
			.permitAll())
	.authorizeHttpRequests(auth->auth
			.requestMatchers("/login").permitAll()
			.requestMatchers("/display").permitAll()
			.requestMatchers("/add").hasRole("ADMIN")
			.requestMatchers("/delete").hasRole("ADMIN")
			.requestMatchers("/**").authenticated()
);
	return http.build();
}

}
