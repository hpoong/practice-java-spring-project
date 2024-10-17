package com.hopoong.redis_queue.api.file.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hopoong.redis_queue.api.file.model.FileQueueModel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.concurrent.CompletableFuture;


@Service
@RequiredArgsConstructor
public class FileQueueService {


    private static final String REDIS_QUEUE_KEY = "file:process";
    private static final String REDIS_ORG_KEY = "file:process:verification";
    private final RedisTemplate<String, Object> redisTemplate;
    private final ObjectMapper objectMapper;



    /*
     * Redis 큐(accountUpdateQueue)에 추가 및 FileQueueModel 객체를 넣음 → 큐에 추가.
     */
    @Async
    public CompletableFuture<Void> asyncWriteFileProcessing(FileQueueModel fileQueueModel) throws JsonProcessingException {

        String luaScript =
                """
                    local queueKey = KEYS[1]
                    local orgKey = KEYS[2]
                    local fileQueueModelJson = ARGV[1]
                    local queueId = ARGV[2]
                    redis.call('LPUSH', queueKey, fileQueueModelJson)
                    redis.call('RPUSH', orgKey, queueId)
                """;

        // 스크립트 실행, 반환값이 없기 때문에 반환 타입을 Void로 지정
        RedisScript<Void> voidRedisScript = RedisScript.of(luaScript, Void.class);

        redisTemplate.execute(
                voidRedisScript,
                List.of(REDIS_QUEUE_KEY, REDIS_ORG_KEY),
                objectMapper.writeValueAsString(fileQueueModel),
                String.valueOf(fileQueueModel.getQueueId())
        );

        return CompletableFuture.completedFuture(null);
    }


    public void WriteFileProcessing(FileQueueModel fileQueueModel) throws JsonProcessingException {

        String luaScript =
                """
                    local queueKey = KEYS[1]
                    local orgKey = KEYS[2]
                    local fileQueueModelJson = ARGV[1]
                    local queueId = ARGV[2]
                    redis.call('LPUSH', queueKey, fileQueueModelJson)
                    redis.call('RPUSH', orgKey, queueId)
                """;

        // 스크립트 실행, 반환값이 없기 때문에 반환 타입을 Void로 지정
        RedisScript<Void> voidRedisScript = RedisScript.of(luaScript, Void.class);

        redisTemplate.execute(
                voidRedisScript,
                List.of(REDIS_QUEUE_KEY, REDIS_ORG_KEY),
                objectMapper.writeValueAsString(fileQueueModel),
                String.valueOf(fileQueueModel.getQueueId())
        );
    }

    /*
     * file 읽고 쓰기
     */
    public synchronized void processTaskTest(FileQueueModel task) {
        Path filePath = Paths.get("src/main/resources/task/task-file.txt");
        String newData = task.getQueueId();
        try (BufferedWriter writer = Files.newBufferedWriter(filePath, StandardOpenOption.APPEND)) {
            writer.write(newData);
            writer.newLine();
            System.out.println("파일에 데이터를 성공적으로 추가했습니다 ::: " + newData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
