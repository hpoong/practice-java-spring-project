package com.hopoong.user_service;

import com.hopoong.core_service.CoreServiceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(CoreServiceConfig.class)
@SpringBootApplication
public class UserServiceApplication {

	public static void main(String[] args) {
		System.setProperty("spring.config.name", "application-core,application-user");
		SpringApplication.run(UserServiceApplication.class, args);
	}

}
