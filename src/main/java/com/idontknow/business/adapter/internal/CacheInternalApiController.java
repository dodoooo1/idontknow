package com.idontknow.business.adapter.internal;

import com.idontknow.business.application.services.LocalCacheManagerService;
import com.idontknow.business.constants.AppUrls;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(CacheInternalApiController.BASE_URL)
@RequiredArgsConstructor
public class CacheInternalApiController {

    public static final String BASE_URL = AppUrls.INTERNAL + "/caches";

    private final LocalCacheManagerService localCacheManagerService;

    @DeleteMapping
    public ResponseEntity<Void> clearCaches() {
        log.info("[request] clearing all local caches");
        this.localCacheManagerService.evictAll();
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{cache-name}")
    public ResponseEntity<Void> clearCaches(@PathVariable("cache-name") final String cacheName) {
        log.info("[request] clearing local cache '{}'", cacheName);
        this.localCacheManagerService.evictByName(cacheName);
        return ResponseEntity.ok().build();
    }


}
