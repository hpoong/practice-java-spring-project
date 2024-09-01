package com.hopoong.redis_transaction.api.transaction.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class TransactionService {

    private final RedisTemplate<String, Object> redisTemplate;

    public boolean transferAmount(String fromAccount, String toAccount, int amount) {
        return redisTemplate.execute((RedisCallback<Boolean>) connection -> {
            // WATCH 명령으로 두 계좌를 모니터링
            connection.watch(fromAccount.getBytes(), toAccount.getBytes());

            // 트랜잭션 시작
            connection.multi();

            // 잔액 확인
            byte[] fromBalanceBytes = connection.get(fromAccount.getBytes());
            byte[] toBalanceBytes = connection.get(toAccount.getBytes());

            int fromBalance = fromBalanceBytes != null ? Integer.parseInt(new String(fromBalanceBytes)) : 0;
            int toBalance = toBalanceBytes != null ? Integer.parseInt(new String(toBalanceBytes)) : 0;

            // 이체할 금액이 충분한지 확인
            if (fromBalance < amount) {
                connection.unwatch();
                return false;  // 잔액 부족으로 트랜잭션 취소
            }

            // 금액 이체
            connection.set(fromAccount.getBytes(), String.valueOf(fromBalance - amount).getBytes());
            connection.set(toAccount.getBytes(), String.valueOf(toBalance + amount).getBytes());

            // 트랜잭션 커밋 (EXEC 명령)
            List<Object> execResults = connection.exec();

            // 트랜잭션 결과 반환 (성공 시 true, 실패 시 false)
            return execResults != null && !execResults.isEmpty();
        });
    }



    public boolean transferAmountS(String fromAccount, String toAccount, int amount) {
        return redisTemplate.execute((RedisCallback<Boolean>) connection -> {
            // 트랜잭션 시작
            connection.multi();

            // 잔액 확인 및 금액 이체 로직을 트랜잭션 내에서 수행
            byte[] fromBalanceBytes = connection.get(fromAccount.getBytes());
            byte[] toBalanceBytes = connection.get(toAccount.getBytes());

            int fromBalance = fromBalanceBytes != null ? Integer.parseInt(new String(fromBalanceBytes)) : 0;
            int toBalance = toBalanceBytes != null ? Integer.parseInt(new String(toBalanceBytes)) : 0;

            // 이체할 금액이 충분한지 확인
            if (fromBalance < amount) {
                connection.discard(); // 잔액이 부족하면 트랜잭션을 취소
                return false;
            }

            // 금액 이체
            connection.set(fromAccount.getBytes(), String.valueOf(fromBalance - amount).getBytes());
            connection.set(toAccount.getBytes(), String.valueOf(toBalance + amount).getBytes());

            // 트랜잭션 커밋 (EXEC 명령)
            List<Object> execResults = connection.exec();

            // 트랜잭션 결과 반환 (성공 시 true, 실패 시 false)
            return execResults != null && !execResults.isEmpty();
        });
    }



}
