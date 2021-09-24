package com.xz.nacos.service;

import com.xz.nacos.domain.User;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

/**
 * @author xz
 * @ClassName CacheServiceImpl
 * @Description
 * @date 2020/12/15 16:44
 **/
@Service
@CacheConfig(cacheNames = {"test","xz"})
public class CacheServiceImpl {
    /**
     * Method缓存 必须指定CacheName, 可以使用@CacheConfig 针对类级别处理
     * condition 针对方法调用前入参校验是否缓存条件 默认true
     * unless 针对方法调用后结果集(result)可以否决缓存 默认false !不否决 = 缓存
     * @author xz
     * @date 2020/12/15
     */
    @Cacheable(key = "'xz' + #key", condition = "#key != 'zs'", unless = "#result == ''")
    public String simpleCacheable(String key) {
        System.out.println("Cacheable 方法");
        return key;
    }

    @Cacheable(key = "T(com.alibaba.fastjson.JSONObject).toJSONString(#user)")
    public User objectCacheAble(User user) {
        System.out.println("Object Cache");
        return user;
    }

    /**
     * 清除缓存
     * 可以指定 cacheName 删除整个cache(默认 false 只删除关联的key, 需要指定allEntries)
     * 同时指定 cacheKey 只删除单个Key
     * @author xz
     * @date 2020/12/15
     */
    @CacheEvict(cacheNames = "test", allEntries = true)
    public void edit() {
        System.out.println("CacheEvict");
    }

    /**
     * 多级缓存 -
     * 取会根据编写顺序依次取值， 取到则不会继续
     * 存则会在两个key中存储值
     * @author xz
     * @date 2020/12/15
     */
    @Caching(cacheable = {@Cacheable(key = "'xz' + #key"), @Cacheable(key = "'xz' + #root.method.getName()")})
    public String selectMultipleCache(String key) {
        System.out.println("Caching ");
        return key;
    }
}
