package com.hopoong.redis_queue.api.file.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hopoong.redis_queue.api.file.model.FileQueueModel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.CompletableFuture;


@Service
@RequiredArgsConstructor
public class FileQueueService {

    @Qualifier("redisQueueTaskExecutor")
    private final TaskExecutor taskExecutor;

    private static final String REDIS_QUEUE_KEY = "file:process";

    private final RedisTemplate<String, Object> redisTemplate;

    private final JedisPool jedisPool;

    private final ObjectMapper objectMapper;


    /*
     * jedis 연동
     */
    @Async
    public CompletableFuture<Void> writeFileProcessingJedis(FileQueueModel fileQueueModel) throws JsonProcessingException {
        String jsonString = objectMapper.writeValueAsString(fileQueueModel);
        redisTemplate.opsForList().leftPush(REDIS_QUEUE_KEY, jsonString);
        return CompletableFuture.completedFuture(null);
    }

    private FileQueueModel parseTaskJedis(String taskJson) throws JsonProcessingException {
        System.out.println(taskJson);
        return objectMapper.readValue(taskJson, FileQueueModel.class);
    }

    public void startTaskProcessorJedis() {
        // TODO : 현재 단일 스레드 처리함 필요시 병렬 처리 필요 (현재는 큐 순서 보장으로 설정)
        taskExecutor.execute(() -> {
            while (true) {

                try (Jedis jedis = jedisPool.getResource()) {
                    while (true) {
                        try {
                            List<String> result = jedis.brpop(0, REDIS_QUEUE_KEY);
                            if (result != null && result.size() > 1) {
                                String taskJson = result.get(1);
                                FileQueueModel task = parseTaskJedis(taskJson);
                                processTask(task);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        });
    }








    /*
     * CompletableFuture
     *
     * Redis 큐(accountUpdateQueue)에 추가 및 accountModel 객체를 넣음
     * → 큐에 추가.
     */
    @Async
    public CompletableFuture<Void> writeFileProcessing(FileQueueModel fileQueueModel) {
        redisTemplate.opsForList().leftPush(REDIS_QUEUE_KEY, fileQueueModel);
        return CompletableFuture.completedFuture(null);
    }




    /*
     * taskExecutor set.
     */
    public void startTaskProcessor() {
        // TODO : 현재 단일 스레드 처리함 필요시 병렬 처리 필요 (현재는 큐 순서 보장으로 설정)
        taskExecutor.execute(() -> {
            while (true) {
                try {
                    // FileQueueModel task = (FileQueueModel) redisTemplate.opsForList().righatPop(REDIS_QUEUE_KEY, 0, TimeUnit.SECONDS);
                    FileQueueModel task = (FileQueueModel) redisTemplate.opsForList().rightPop(REDIS_QUEUE_KEY);
                    if (task != null) {
                        processTask(task);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    /*
     * file 읽고 쓰기
     */
    private void processTask(FileQueueModel task) throws InterruptedException {

        synchronized(this) {

            Thread.sleep(5000); // 5초

            Path filePath = Paths.get("src/main/resources/task/task-file.txt");

            String newData = task.getQueueId();

            try {

                List<String> lines = Files.readAllLines(filePath);

                try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
                    for (String line : lines) {
                        writer.newLine();
                        writer.write(line);
                    }
                    writer.newLine();
                    writer.write(newData + "\n");
                }

                System.out.println("파일에 데이터를 성공적으로 추가했습니다 ::: " + newData);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }




}
