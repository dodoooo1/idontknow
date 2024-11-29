package com.idontknow.business.infra.configs.redis;

import lombok.RequiredArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DistributedLockExample {
    private final RedissonClient redissonClient;
    private final RedisTemplate<String, Object> redisTemplate;

    public void test() {

        RLock lock = redissonClient.getLock("myLock");
        redisTemplate.opsForValue().set("key", "wqeqeqwe");
        try {
            // Attempt to acquire the lock
            if (lock.tryLock()) {
                try {
                    // Critical section
                    System.out.println("Lock acquired, executing critical section");
                    // Simulate some work
                    Thread.sleep(1000);
                } finally {
                    // Ensure the lock is released
                    lock.unlock();
                    System.out.println("Lock released");
                }
            } else {
                System.out.println("Could not acquire lock");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
