package com.xz.nacos.controller;

import com.xz.nacos.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author xz
 * @ClassName NacosController
 * @Description
 * @date 2020/11/6 0006 22:34
 **/
@RestController
public class NacosController {

    @Autowired
    DiscoveryClient client;
    @RequestMapping("/fileUpload")
    public void testFileUploadAOPLog(MultipartFile file) {
        System.out.println(file);
    }
    @RequestMapping("/simple")
    public void testSimpleParamAOPLog(String username, Integer age) {
        System.out.println(username);
    }

    @RequestMapping("/object")
    public User testObjectParamAOPLog(@RequestBody User user) {
        System.out.println(user);
        return user;
    }
    @RequestMapping("/exception")
    public void testExceptionAOPLog() throws Exception {
        throw new Exception("服务器异常");
    }
}
