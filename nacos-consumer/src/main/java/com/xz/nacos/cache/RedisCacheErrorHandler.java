package com.xz.nacos.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.interceptor.CacheErrorHandler;

/**
 * @author xz
 * @ClassName RedisCacheErrorHandler
 * @Description
 * @date 2020/12/16 9:54
 **/
public class RedisCacheErrorHandler implements CacheErrorHandler {
    Logger log = LoggerFactory.getLogger(RedisCacheErrorHandler.class);

    @Override
    public void handleCacheGetError(RuntimeException exception, Cache cache, Object key) {
        log.error("获取缓存失败, key {}", key);
    }

    @Override
    public void handleCachePutError(RuntimeException exception, Cache cache, Object key, Object value) {
        log.error("修改缓存失败, key {}", key);
    }

    @Override
    public void handleCacheEvictError(RuntimeException exception, Cache cache, Object key) {
        log.error("处理 cacheEvict失败, key {}", key);
    }

    @Override
    public void handleCacheClearError(RuntimeException exception, Cache cache) {
        log.error("清除缓存失败");
    }
}
