package com.xz.nacos.cache;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheWriter;

/**
 * @author xz
 * @ClassName CustomRedisCache
 * @Description
 * @date 2020/12/26 22:50
 **/
public class CustomRedisCache extends RedisCache {

    private final String name;
    private final RedisCacheWriter cacheWriter;
    private final RedisCacheConfiguration cacheConfig;
    private final ConversionService conversionService;

    /**
     * Create new {@link RedisCache}.
     *  @param name        must not be {@literal null}.
     * @param cacheWriter must not be {@literal null}.
     * @param cacheConfig must not be {@literal null}.
     * @param name1
     * @param cacheWriter1
     * @param cacheConfig1
     * @param conversionService
     */
    protected CustomRedisCache(String name, RedisCacheWriter cacheWriter, RedisCacheConfiguration cacheConfig, String name1, RedisCacheWriter cacheWriter1, RedisCacheConfiguration cacheConfig1, ConversionService conversionService) {
        super(name, cacheWriter, cacheConfig);
        this.name = name;
        this.cacheWriter = cacheWriter;
        this.cacheConfig = cacheConfig;
        this.conversionService = cacheConfig.getConversionService();
    }

    @Override
    public void evict(Object key) {
        // 判断key是否属于模糊删除
        if (StringUtils.startsWith("*", key.toString())
                || StringUtils.endsWith("*", key.toString())) {
            cacheWriter.clean(name, conversionService.convert(createCacheKey(key), byte[].class));
            return ;
        }
        super.evict(key);
    }
}
