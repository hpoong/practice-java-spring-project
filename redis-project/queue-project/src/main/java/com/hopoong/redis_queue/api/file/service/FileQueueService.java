package com.hopoong.redis_queue.api.file.service;

import com.hopoong.redis_queue.api.file.model.FileQueueModel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

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
        for (int i = 0; i < 10; i++) {
            taskExecutor.execute(() -> {
                while (true) {
                    try {
                        FileQueueModel task = (FileQueueModel) redisTemplate.opsForList().rightPop(REDIS_QUEUE_KEY);
//                        AccountModel task = (AccountModel) redisTemplate.opsForList().rightPop(REDIS_QUEUE_KEY, 0, TimeUnit.SECONDS);
                        if (task != null) {
                            processTask(task);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
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
