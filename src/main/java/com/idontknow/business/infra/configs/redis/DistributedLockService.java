package com.idontknow.business.infra.configs.redis;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class DistributedLockService {

    @Autowired
    private RedissonClient redissonClient;

    /**
     * 加锁方法
     *
     * @param lockName  锁名称
     * @param waitTime  等待时间（单位：秒）
     * @param leaseTime 租赁时间（单位：秒）
     * @return 是否成功获取锁
     */
    public boolean tryLock(String lockName, long waitTime, long leaseTime) {
        RLock lock = redissonClient.getLock(lockName);
        try {
            return lock.tryLock(waitTime, leaseTime, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }

    /**
     * 解锁方法
     *
     * @param lockName 锁名称
     */
    public void unlock(String lockName) {
        RLock lock = redissonClient.getLock(lockName);
        if (lock.isHeldByCurrentThread()) {
            lock.unlock();
        }
    }

    /**
     * 强制解锁方法
     *
     * @param lockName 锁名称
     */
    public void forceUnlock(String lockName) {
        RLock lock = redissonClient.getLock(lockName);
        lock.forceUnlock();
    }

    /**
     * 自动释放锁的方法
     *
     * @param lockName  锁名称
     * @param waitTime  等待时间（单位：秒）
     * @param leaseTime 租赁时间（单位：秒）
     * @param task      要执行的任务
     * @throws InterruptedException 如果线程中断
     */
    public void executeWithLock(String lockName, long waitTime, long leaseTime, Runnable task) throws InterruptedException {
        RLock lock = redissonClient.getLock(lockName);
        try {
            boolean isLocked = lock.tryLock(waitTime, leaseTime, TimeUnit.SECONDS);
            if (isLocked) {
                try {
                    task.run();
                } finally {
                    lock.unlock();
                }
            } else {
                throw new RuntimeException("Failed to acquire lock for " + lockName);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw e;
        }
    }
}



