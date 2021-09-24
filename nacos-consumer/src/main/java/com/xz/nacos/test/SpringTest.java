package com.xz.nacos.test;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author xz
 * @ClassName SpringTest
 * @Description
 * @date 2020/12/30 12:27
 **/
@Configuration
public class SpringTest {
    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringTest.class);
        User user = context.getBean("user", User.class);
        System.out.println(user);
        Method test = User.class.getMethod("test");
        test.invoke(user);
    }

    @Component("user")
    @Configuration
    static class User {
        @Autowired
        BeanFactory beanFactory;

        public void test() {
            System.out.println(beanFactory);
        }
    }
}
