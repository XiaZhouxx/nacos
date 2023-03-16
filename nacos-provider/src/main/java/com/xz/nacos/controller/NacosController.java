package com.xz.nacos.controller;

import com.xz.nacos.service.NacosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.*;

/**
 * @author xz
 * @ClassName NacosController
 * @Description
 * @date 2021/9/23 0023 13:03
 **/
@RestController
@RequestMapping
public class NacosController {
//    @Autowired
//    NacosService nacosService;
//    @RequestMapping("/say")
//    public String say() {
//        return nacosService.service();
//    }
//
//    @RequestMapping("/test")
//    public String test() {
//        return nacosService.service1();
//    }

    public static void main(String[] args) {
        ThreadPoolExecutor exec = new ThreadPoolExecutor(2, 2, 0, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(1024), r -> new Thread(r, "test-"), new ThreadPoolExecutor.DiscardPolicy());

        exec.prestartAllCoreThreads();
    }


}
