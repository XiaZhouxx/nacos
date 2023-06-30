package com.xz.nacos.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * remark.
 *
 * @author xz
 * @date 2022/11/2 11:50
 */
@RestController
public class UserController {

    @RequestMapping("/register")
    public Object register() {

        return "success";
    }

    @RequestMapping("/testHtml")
    public Map testHtml(@RequestBody Map<String, Object> body) {

        return body;
    }
}
