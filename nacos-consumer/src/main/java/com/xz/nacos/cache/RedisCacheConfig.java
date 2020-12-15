package com.xz.nacos.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.stereotype.Component;

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

    @Override
    public CacheManager cacheManager() {
        return RedisCacheManager.create(redisConnectionFactory);
    }
}
