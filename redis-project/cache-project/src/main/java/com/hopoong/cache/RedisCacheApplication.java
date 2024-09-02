package com.hopoong.cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;


@SpringBootApplication
@PropertySource("file:./pwd.ini")
public class RedisCacheApplication {
	public static void main(String[] args) {
		SpringApplication.run(RedisCacheApplication.class, args);
	}
}
