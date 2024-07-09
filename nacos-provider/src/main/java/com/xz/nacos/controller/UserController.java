package com.xz.nacos.controller;

import com.xz.nacos.service.tx.TransactionService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * remark.
 *
 * @author xz
 * @date 2022/11/2 11:50
 */
@RestController
@Transactional
public class UserController {

    @Resource
    TransactionService ts;

    /**
     * 私有方法, 如果此时Controller被代理过, 那么调用的是被代理后对象的私n有方法,
     * 此时这个代理对象并没有被注入依赖，那么方法中使用依赖会导致空指针, 没有代理则不会导致这个问题
     * @return
     */
    @RequestMapping("/register")
    private Object register() {
        ts.service();
        return "success";
    }

    @RequestMapping("/testHtml")
    public Map testHtml(@RequestBody Map<String, Object> body) {

        return body;
    }
}
