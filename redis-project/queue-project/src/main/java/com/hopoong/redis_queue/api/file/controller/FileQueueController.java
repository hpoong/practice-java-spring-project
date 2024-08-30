package com.hopoong.redis_queue.api.file.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hopoong.redis_queue.api.file.model.FileQueueModel;
import com.hopoong.redis_queue.api.file.service.FileQueueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
public class FileQueueController {

    private final FileQueueService fileQueueService;

    @GetMapping("/write/enqueue")
    public CompletableFuture<ResponseEntity<String>> writeFileProcessing() throws JsonProcessingException {
        FileQueueModel fileQueueModel = new FileQueueModel();
        fileQueueModel.setQueueId(UUID.randomUUID().toString().substring(0, 5));
        return fileQueueService.writeFileProcessingJedis(fileQueueModel)
                .thenApply(result -> ResponseEntity.ok("Account balance update request submitted for account " + fileQueueModel.getQueueId()));
    }

}
