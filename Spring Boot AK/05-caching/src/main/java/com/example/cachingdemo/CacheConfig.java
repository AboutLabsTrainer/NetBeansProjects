package com.example.cachingdemo;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * Schaltet das Caching ein und stellt den Caffeine-CacheManager bereit.
 * Die Regionennamen entsprechen den Cache-Namen an den Service-Methoden. Ohne diese Bean gäbe es keinen gemeinsamen Cache.
 */
@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager(
                "additions",
                "subtractions",
                "multiplications",
                "divisions",
                "powers"
        );

        cacheManager.setCaffeine(
                Caffeine.newBuilder()
                        .maximumSize(100)
                        .expireAfterWrite(10, TimeUnit.MINUTES)
                        .recordStats()
        );

        return cacheManager;
    }
}
