package com.global.Configration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.global.Service.CustomSuccessHandler;
import com.global.Service.User_Details_Service;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private CustomSuccessHandler customSuccessHandler ;
	@Autowired
	private User_Details_Service user_Details_Service;
	
	
	
	@Bean
	public static PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	
	@Bean 
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf(c -> c.disable())
		.authorizeHttpRequests(request -> request.requestMatchers("/adminPage").hasAuthority("ADMIN")
				.requestMatchers("/userPage").hasAuthority("USER") 
				.requestMatchers("/register",
								"/js/**",
								"/css/**",
		                		"/img/**").permitAll()
				.anyRequest().authenticated())
				
				.formLogin(form -> form.loginPage("/login").loginProcessingUrl("/login")
						.successHandler(customSuccessHandler).permitAll())
				
				.logout(form -> form.invalidateHttpSession(true).clearAuthentication(true)
						.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
						.logoutSuccessUrl("/login?logout").permitAll());
	
	return httpSecurity.build();
	}
	




	@Autowired
	public void Configure (AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(user_Details_Service).passwordEncoder(passwordEncoder());
		
	}






















}
