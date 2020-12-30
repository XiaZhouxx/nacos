package com.xz.nacos.cache;

import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;

import java.util.Map;

/**
 * @author xz
 * @ClassName CustomeRedisCacheManager
 * @Description
 * @date 2020/12/26 21:15
 **/
public class CustomRedisCacheManager extends RedisCacheManager {

    @Override
    protected RedisCache createRedisCache(String name, RedisCacheConfiguration cacheConfig) {
        return super.createRedisCache("123" + name, cacheConfig);
    }

    public CustomRedisCacheManager(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration) {
        super(cacheWriter, defaultCacheConfiguration);
    }

    public CustomRedisCacheManager(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration, String... initialCacheNames) {
        super(cacheWriter, defaultCacheConfiguration, initialCacheNames);
    }

    public CustomRedisCacheManager(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration, boolean allowInFlightCacheCreation, String... initialCacheNames) {
        super(cacheWriter, defaultCacheConfiguration, allowInFlightCacheCreation, initialCacheNames);
    }

    public CustomRedisCacheManager(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration, Map<String, RedisCacheConfiguration> initialCacheConfigurations) {
        super(cacheWriter, defaultCacheConfiguration, initialCacheConfigurations);
    }

    public CustomRedisCacheManager(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration, Map<String, RedisCacheConfiguration> initialCacheConfigurations, boolean allowInFlightCacheCreation) {
        super(cacheWriter, defaultCacheConfiguration, initialCacheConfigurations, allowInFlightCacheCreation);
    }
}
