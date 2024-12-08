/*
package com.idontknow.business.infra.cache;

*/
/**
 * @description:
 * @title: CacheConfig
 * @package com.idontknow.business.infra.cache
 * @author: glory
 * @date: 2024/10/24 21:41
 *//*


import com.github.benmanes.caffeine.cache.Caffeine;
import org.redisson.api.RedissonClient;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfig {

    // 配置 Caffeine CacheManager
    @Bean
    @ConditionalOnProperty(name = "spring.cache.type", havingValue = "caffeine", matchIfMissing = true)
    CacheManager cacheManager(Caffeine<Object, Object> caffeine) {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager("CaffeineCache");
        cacheManager.setCaffeine(caffeine);
        return cacheManager;
    }

    @Bean(name = "caffeine")
    Caffeine<Object, Object> caffeineCacheBuilder() {
        return Caffeine.newBuilder()
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .maximumSize(100)
                .recordStats();
    }

    // 配置 Redis CacheManager
    @Bean
    @ConditionalOnProperty(name = "spring.cache.type", havingValue = "redis")
    public RedissonSpringCacheManager redisCacheManager(RedissonClient redissonClient) {
        return new RedissonSpringCacheManager(redissonClient);

    }
}

*/
