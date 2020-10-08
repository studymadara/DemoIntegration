package com.integration.bundle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

@SpringBootApplication
public class BundleApplication {

	public static void main(String[] args) {
		SpringApplication.run(BundleApplication.class, args);
	}

}
