package com.hopoong.redis_transaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;


@SpringBootApplication
@PropertySource("file:./pwd.ini")
public class RedisTransactionApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedisTransactionApplication.class, args);
	}

}
