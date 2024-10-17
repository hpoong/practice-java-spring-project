package com.hopoong.redis_queue;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;


@SpringBootApplication
@RequiredArgsConstructor
@EnableAsync
public class RedisQueueApplication {
	public static void main(String[] args) {
		SpringApplication.run(RedisQueueApplication.class, args);
	}
}
