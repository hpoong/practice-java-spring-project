package com.hopoong.redis_queue.api.account.controller;

import com.hopoong.redis_queue.api.account.model.AccountModel;
import com.hopoong.redis_queue.api.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/update")
    public CompletableFuture<ResponseEntity<String>> updateAccountBalance() {
        AccountModel accountModel = new AccountModel();
        accountModel.setAccountId(UUID.randomUUID().toString().substring(0, 5));
        return accountService.updateAccountBalanceAsync(accountModel)
                .thenApply(result -> ResponseEntity.ok("Account balance update request submitted for account " + accountModel.getAccountId()));
    }

}
