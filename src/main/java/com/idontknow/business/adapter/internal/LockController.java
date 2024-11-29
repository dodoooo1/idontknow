package com.idontknow.business.adapter.internal;

import com.idontknow.business.infra.configs.redis.DistributedLockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LockController {

    private static final Logger logger = LoggerFactory.getLogger(LockController.class);

    @Autowired
    private DistributedLockService distributedLockService;

    @GetMapping("/lock")
    public String acquireLock(@RequestParam String lockName,
                             @RequestParam(required = false, defaultValue = "10") long waitTime,
                             @RequestParam(required = false, defaultValue = "30") long leaseTime) {
        boolean locked = distributedLockService.tryLock(lockName, waitTime, leaseTime);
        if (locked) {
            logger.info("Lock acquired successfully for {}", lockName);
            return "Lock acquired successfully for " + lockName;
        } else {
            logger.warn("Failed to acquire lock for {}", lockName);
            return "Failed to acquire lock for " + lockName;
        }
    }

    @GetMapping("/unlock")
    public String releaseLock(@RequestParam String lockName) {
        distributedLockService.unlock(lockName);
        logger.info("Lock released successfully for {}", lockName);
        return "Lock released successfully for " + lockName;
    }

    @GetMapping("/force-unlock")
    public String forceReleaseLock(@RequestParam String lockName) {
        distributedLockService.forceUnlock(lockName);
        logger.warn("Lock forcefully released for {}", lockName);
        return "Lock forcefully released for " + lockName;
    }

    @GetMapping("/execute-with-lock")
    public String executeWithLock(@RequestParam String lockName,
                                  @RequestParam(required = false, defaultValue = "10") long waitTime,
                                  @RequestParam(required = false, defaultValue = "30") long leaseTime) throws InterruptedException {
        try {
            distributedLockService.executeWithLock(lockName, waitTime, leaseTime, () -> {
                // 模拟任务执行
                logger.info("Executing task with lock: {}", lockName);
                try {
                    Thread.sleep(5000); // 模拟长时间任务
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
            logger.info("Task completed with lock: {}", lockName);
            return "Task completed with lock: " + lockName;
        } catch (RuntimeException e) {
            logger.error("Error executing task with lock: {}", lockName, e);
            return "Error executing task with lock: " + lockName;
        }
    }
}






