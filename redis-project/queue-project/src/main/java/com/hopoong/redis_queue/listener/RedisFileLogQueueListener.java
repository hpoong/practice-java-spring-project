package com.hopoong.redis_queue.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hopoong.redis_queue.api.file.model.FileQueueModel;
import com.hopoong.redis_queue.api.file.service.FileQueueService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@EnableScheduling
@RequiredArgsConstructor
public class RedisFileLogQueueListener {

    private final String REDIS_QUEUE_KEY = "file:process";
    private final RedisTemplate<String, Object> redisTemplate;
    private final FileQueueService fileQueueService;
    private final ObjectMapper objectMapper;


    @Scheduled(fixedDelay = 5000L) // 5초
    public void taskScheduled() throws JsonProcessingException {
        String taskStr;
        while ((taskStr = (String) redisTemplate.opsForList().rightPop(REDIS_QUEUE_KEY)) != null) {
            FileQueueModel task = objectMapper.readValue(taskStr, FileQueueModel.class);
            fileQueueService.processTaskTest(task);
        }
    }
}
