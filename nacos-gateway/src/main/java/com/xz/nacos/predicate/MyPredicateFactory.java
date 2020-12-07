package com.xz.nacos.predicate;

import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.web.server.ServerWebExchange;

import java.util.function.Predicate;

/**
 * @author xz
 * @ClassName MyPredicate
 * @Description
 * @date 2020/12/7 9:49
 **/
public class MyPredicateFactory extends AbstractRoutePredicateFactory<MyPredicateFactory.Config> {

    public MyPredicateFactory() {
        super(Config.class);
    }

    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        return null;
    }


    /**
     * 自定义配置项
     * @author xz
     * @date 2020/12/7
     */
    static class Config {
        private String name;

        private String value;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
