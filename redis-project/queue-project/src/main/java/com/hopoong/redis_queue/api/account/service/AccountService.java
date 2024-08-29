package com.hopoong.redis_queue.api.account.service;

import com.hopoong.redis_queue.api.account.model.AccountModel;
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
import java.util.concurrent.TimeUnit;


@Service
@RequiredArgsConstructor
public class AccountService {

    @Qualifier("redisQueueTaskExecutor")
    private final TaskExecutor taskExecutor;


    private static final String REDIS_QUEUE_KEY = "accountUpdateQueue";

    private final RedisTemplate<String, Object> redisTemplate;




    /*
     * CompletableFuture
     * opsForList
     * leftPush
     *
     * Redis 큐(accountUpdateQueue)에 추가 및 accountModel 객체를 넣음
     * → 큐에 추가.
     */
    @Async
    public CompletableFuture<Void> updateAccountBalanceAsync(AccountModel accountModel) {
        redisTemplate.opsForList().leftPush(REDIS_QUEUE_KEY, accountModel);
        return CompletableFuture.completedFuture(null);
    }



//    public void startTaskProcessor() {
//        for (int i = 0; i < 10; i++) {
//            taskExecutor.execute(() -> {
//                while (true) {
//                    try {
//                        // BLPOP 또는 BRPOP으로 큐에서 대기하며 요소를 가져옴
//                        AccountModel task = (AccountModel) redisTemplate.opsForList().rightPop(REDIS_QUEUE_KEY, 0, TimeUnit.SECONDS);
//
//                        if(task != null) {
//                            processTask(task);
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                        break;
//                    }
//                }
//            });
//        }
//    }

    /*
     * rightPop
     */
    public void startTaskProcessor() {
        new Thread(() -> {
            while (true) {
                try {

                    // TODO : after
                    // BLPOP 또는 BRPOP으로 큐에서 대기하며 요소를 가져옴
//                    AccountModel task = (AccountModel) redisTemplate.opsForList().rightPop(REDIS_QUEUE_KEY, 0, TimeUnit.SECONDS);

                    // TODO : before
                     AccountModel task = (AccountModel) redisTemplate.opsForList().rightPop(REDIS_QUEUE_KEY);
                    if(task != null) {
                        processTask(task);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    break;
                }
            }
        }).start();
    }


    private void processTask(AccountModel task) throws InterruptedException {

        synchronized(this) {

            Thread.sleep(5000); // 5초

            // resources/task/task-file.txt 경로를 설정합니다.
            Path filePath = Paths.get("src/main/resources/task/task-file.txt");

            // 추가할 데이터
            String newData = task.getAccountId();

            try {

                // 마지막 줄에 개행 하고 데이터 추가
                List<String> lines = Files.readAllLines(filePath);

                try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
                    for (String line : lines) {
                        writer.write(line);
                        writer.newLine();
                    }
                    writer.newLine();
                    writer.write(newData);
                }

                System.out.println("파일에 데이터를 성공적으로 추가했습니다.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }




}
