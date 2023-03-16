package com.xz.nacos.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * remark.
 *
 * @author xz
 * @date 2022/11/2 11:52
 */
@FeignClient(value = "nacos-provider")
public interface UserService {
    @RequestMapping("/register")
    Object registerUser();
}
