package com.idontknow.business.application.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

/**
 * LocalCacheManagerService:
 *
 * <p>Only use this services if the cache provider is concurrentHashMap. It provides utility function
 * to help clearing local caches.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class LocalCacheManagerService {
    private final CacheManager cacheManager;


    public void evictByName(final String cacheName) {
        final Cache cache = this.cacheManager.getCache(cacheName);
        if (cache != null) {
            cache.clear();
            log.info("[cache-eviction] evicted local cache '{}'", cacheName);
        }
    }

    public void evictAll() {
        this.cacheManager.getCacheNames().forEach(name -> this.cacheManager.getCache(name).clear());
        log.info("[cache-eviction] evicted all local caches");
    }
}
