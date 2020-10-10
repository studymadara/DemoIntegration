package com.integration.bundle.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class BundleSecurity extends WebSecurityConfigurerAdapter
{
	@Override
	protected void configure(HttpSecurity http) throws Exception 
	{
		http
			.antMatcher("/**").authorizeRequests()
			.antMatchers(new String[]{"/loginPage","/uploadData","/docxUpload","/generateFile"}).permitAll()
			.anyRequest().authenticated()
			.and()
			.oauth2Login()
			.and()
			.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/loginPage");
	}
}
