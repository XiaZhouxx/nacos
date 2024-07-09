package com.xz.nacos;

import com.xz.nacos.domain.ReflectPerson;
import com.xz.nacos.domain.ReflectUser;
import com.xz.nacos.domain.User;

import java.lang.reflect.Method;

/**
 * @author xz
 * @since 2024/7/9 10:14
 */
public class ReflectTest {
    public static void main(String[] args) throws Exception {
        ReflectUser user = new ReflectUser();

        Class<? extends ReflectPerson> aClass = user.getClass();

        Method method = aClass.getDeclaredMethod("say");

        method.setAccessible(true);

        method.invoke(user);
    }
}
