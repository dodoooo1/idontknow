package com.idontknow.business.infra.configs.redis;

import org.redisson.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisService {


    private final RedissonClient redissonClient;

    public RedisService(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    /**
     * 存储对象到 Redis
     *
     * @param key   键
     * @param value 值
     * @param ttl   过期时间（单位：秒）
     */
    public void setValue(String key, Object value, long ttl) {
        RBucket<Object> bucket = redissonClient.getBucket(key);
        bucket.set(value, ttl, TimeUnit.SECONDS);
    }

    /**
     * 获取对象从 Redis
     *
     * @param key 键
     * @return 对象
     */
    public Object getValue(String key) {
        RBucket<Object> bucket = redissonClient.getBucket(key);
        return bucket.get();
    }

    /**
     * 删除键值对
     *
     * @param key 键
     */
    public void deleteValue(String key) {
        RBucket<Object> bucket = redissonClient.getBucket(key);
        bucket.delete();
    }

    /**
     * 检查键是否存在
     *
     * @param key 键
     * @return 是否存在
     */
    public boolean exists(String key) {
        RBucket<Object> bucket = redissonClient.getBucket(key);
        return bucket.isExists();
    }

    /**
     * 存储哈希表到 Redis
     *
     * @param mapName 哈希表名称
     * @param key     键
     * @param value   值
     * @param ttl     过期时间（单位：秒）
     */
    public void setMapValue(String mapName, String key, Object value, long ttl) {
        RMapCache<String, Object> mapCache = redissonClient.getMapCache(mapName);
        mapCache.put(key, value, ttl, TimeUnit.SECONDS);
    }

    /**
     * 获取哈希表中的值
     *
     * @param mapName 哈希表名称
     * @param key     键
     * @return 值
     */
    public Object getMapValue(String mapName, String key) {
        RMapCache<String, Object> mapCache = redissonClient.getMapCache(mapName);
        return mapCache.get(key);
    }

    /**
     * 删除哈希表中的键值对
     *
     * @param mapName 哈希表名称
     * @param key     键
     */
    public void deleteMapValue(String mapName, String key) {
        RMapCache<String, Object> mapCache = redissonClient.getMapCache(mapName);
        mapCache.remove(key);
    }

    /**
     * 检查哈希表中的键是否存在
     *
     * @param mapName 哈希表名称
     * @param key     键
     * @return 是否存在
     */
    public boolean mapKeyExists(String mapName, String key) {
        RMapCache<String, Object> mapCache = redissonClient.getMapCache(mapName);
        return mapCache.containsKey(key);
    }
}



