package com.xz.nacos.controller;

import com.xz.nacos.domain.User;
import com.xz.nacos.service.CacheServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xz
 * @ClassName CacheController
 * @Description
 * @date 2020/12/15 16:46
 **/
@RestController
public class CacheController {
    @Autowired
    CacheServiceImpl cacheService;

    /**
     * 缓存
     * @author xz
     * @date 2020/12/15
     */
    @RequestMapping("/cache")
    public String cacheAble(String name) {
        cacheService.simpleCacheable(name);
        return name;
    }

    @RequestMapping("/objectCache")
    public User objectCacheAble(User user) {
        return cacheService.objectCacheAble(user);
    }

    /**
     * 移除缓存
     * @author xz
     * @date 2020/12/15
     */
    @RequestMapping("/cacheEvict")
    public String cacheEvict() {
        cacheService.edit();
        return "success";
    }

    /**
     * 多缓存
     * @author xz
     * @date 2020/12/15
     */
    @RequestMapping("/caching")
    public String multipleCache(String name) {
        cacheService.selectMultipleCache(name);
        return name;
    }
}
