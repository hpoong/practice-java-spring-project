package com.hopoong.auth_service;

import com.hopoong.core_service.CoreServiceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(CoreServiceConfig.class)
@SpringBootApplication
public class AuthServiceApplication {

	public static void main(String[] args) {
		System.setProperty("spring.config.name", "application-core,application-auth");
		SpringApplication.run(AuthServiceApplication.class, args);
	}

}
