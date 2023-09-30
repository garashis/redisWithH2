package com.example.redisAndMongo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;

import java.time.Duration;
import java.util.Map;

/**
 * Configuration for Redis Cache
 */
@Configuration
public class RedisConfig {

    /**
     * RedisCacheManagerBuilderCustomizer allows to set RedisCacheManager configuration like set custom value of TTL
     * for a given Cache with method named entryTtl(Duration).
     *
     * @return instance of RedisCacheManagerBuilderCustomizer
     */
    @Bean
    RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer(@Value("${spring.data.redis" +
            ".time-to-live-seconds}") final long seconds) {
        return builder -> builder.withInitialCacheConfigurations(Map.ofEntries(Map.entry("findByFirstName",
                RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(seconds)))));

    }
}
