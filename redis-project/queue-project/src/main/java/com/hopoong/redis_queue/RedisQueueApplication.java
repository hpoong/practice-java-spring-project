package com.hopoong.redis_queue;

import com.hopoong.redis_queue.api.file.service.FileQueueService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;


@SpringBootApplication
@PropertySource("file:./pwd.ini")
@RequiredArgsConstructor
public class RedisQueueApplication implements CommandLineRunner {

	private final FileQueueService fileQueueService;

	public static void main(String[] args) {
		SpringApplication.run(RedisQueueApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		fileQueueService.startTaskProcessor();
	}
}
