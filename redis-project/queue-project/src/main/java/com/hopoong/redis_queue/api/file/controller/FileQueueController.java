package com.hopoong.redis_queue.api.file.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hopoong.redis_queue.api.file.model.FileQueueModel;
import com.hopoong.redis_queue.api.file.service.FileQueueService;
import com.hopoong.redis_queue.response.CommonResponseCodeEnum;
import com.hopoong.redis_queue.response.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
public class FileQueueController {

    private final FileQueueService fileQueueService;


    @PostMapping("/async/write/enqueue")
    public CompletableFuture<ResponseEntity<SuccessResponse>> asyncWriteFileProcessing(@RequestBody FileQueueModel fileQueueModel) throws JsonProcessingException {
        return fileQueueService.asyncWriteFileProcessing(fileQueueModel)
                .thenApply(result -> ResponseEntity.status(200)
                        .body(new SuccessResponse(CommonResponseCodeEnum.C01, "Update request submitted for id " + fileQueueModel.getQueueId())))
                .exceptionally(ex -> ResponseEntity.status(500)
                        .body(new SuccessResponse(CommonResponseCodeEnum.C01, "Error processing file: " + ex.getMessage())));
    }


    @PostMapping("/write/enqueue")
    public ResponseEntity<SuccessResponse> writeFileProcessing(@RequestBody FileQueueModel fileQueueModel) throws JsonProcessingException {
        fileQueueService.WriteFileProcessing(fileQueueModel);
        return ResponseEntity.status(200)
                .body(new SuccessResponse(CommonResponseCodeEnum.C01, "Update request submitted for id " + fileQueueModel.getQueueId()));
    }

}
