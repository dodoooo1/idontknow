package com.idontknow.business.infra.configs.security.auth.providers;

import lombok.RequiredArgsConstructor;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class JwtTokenStoreService {
    private final RedissonClient redissonClient;

    @Value("${jwt.access.token.validity.seconds}")
    private Long accessTokenValiditySeconds;

    public void storeToken(String token) {
        RBucket<String> bucket = redissonClient.getBucket("tokens:" + token);
        bucket.setAsync(token, accessTokenValiditySeconds, TimeUnit.MILLISECONDS);
    }

    public void blacklistToken(String token) {
        RBucket<String> bucket = redissonClient.getBucket("blacklisted_tokens:" + token);
        bucket.setAsync(token, accessTokenValiditySeconds, TimeUnit.MILLISECONDS);
    }

    public boolean isTokenStored(String token) {
        RBucket<String> bucket = redissonClient.getBucket("tokens:" + token);
        return bucket.isExists();
    }

    public boolean isTokenBlacklisted(String token) {
        RBucket<String> bucket = redissonClient.getBucket("blacklisted_tokens:" + token);
        return bucket.isExists();
    }
}
