package com.idontknow.business.infra.configs.redis;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfig {

    @Bean
    public RedissonClient redissonClient() {
        // 使用 YAML 或 JSON 配置文件加载
        Config config = new Config();
        config.useSingleServer().setAddress("redis://localhost:6379");

        return Redisson.create(config);
    }
}
