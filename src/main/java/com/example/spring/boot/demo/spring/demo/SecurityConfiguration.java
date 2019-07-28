package com.example.spring.boot.demo.spring.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	auth.inMemoryAuthentication().withUser("jimmy").password("{noop}pass").roles("USER").and().withUser("admin")
		.password("{noop}pass").roles("USER", "ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
	http.httpBasic().and().authorizeRequests().antMatchers(HttpMethod.GET, "/bonds/**").hasRole("USER")
		.antMatchers(HttpMethod.POST, "/bonds").hasRole("ADMIN").antMatchers(HttpMethod.PUT, "/bonds/**")
		.hasRole("ADMIN").antMatchers(HttpMethod.DELETE, "/bonds/**").hasRole("ADMIN").and().csrf().disable()
		.formLogin().disable();
    }
}
