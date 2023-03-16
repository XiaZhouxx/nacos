package com.xz.nacos.feign.config;

import com.xz.nacos.domain.UserInterface;
import feign.codec.Decoder;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Proxy;
import java.util.Base64;
import java.util.Random;

/**
 * remark.
 *
 * @author xz
 * @date 2022/11/2 11:54
 */
@Configuration
public class UserFeignConfig {
    public static void main(String[] args) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        UserInterface user = (UserInterface) Proxy.newProxyInstance(UserFeignConfig.class.getClassLoader(), new Class[]{UserInterface.class}, ((proxy, method, args1) -> {
            System.out.println("proxy");
            return null;
        }));

        user.songSing();
    }
}
