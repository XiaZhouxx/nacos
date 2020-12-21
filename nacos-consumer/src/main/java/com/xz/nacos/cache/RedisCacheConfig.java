package com.xz.nacos.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

import java.time.Duration;

/**
 * @author xz
 * @ClassName RedisCacheConfig
 * @Description
 * @date 2020/12/15 16:05
 **/
@Component
public class RedisCacheConfig extends CachingConfigurerSupport {

    @Autowired
    RedisConnectionFactory redisConnectionFactory;
    @Value("${order.redis.cachePrefix:saas:cache:}")
    private String cachePrefix;
    @Value("${order.redis.cacheTtl:60000}")
    private long cacheTtl;

    @Override
    public CacheManager cacheManager() {
        RedisCacheWriter writer = RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory);
        // 配置项
        RedisCacheConfiguration configuration = RedisCacheConfiguration
                .defaultCacheConfig()
                .serializeValuesWith(
                        RedisSerializationContext
                                .SerializationPair
                                .fromSerializer(RedisSerializer.json())
                )
                // .prefixCacheNameWith(cachePrefix)
                .computePrefixWith(cacheName -> cachePrefix + cacheName) // 构建前缀的方式，不加 cacheName则缓存全在一个prefix中
                .entryTtl(Duration.ofMillis(cacheTtl))
                .disableCachingNullValues();
        return new RedisCacheManager(writer, configuration);
    }

    @Override
    public CacheErrorHandler errorHandler() {
        return super.errorHandler();
    }

}
