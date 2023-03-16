package com.xz.nacos.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
