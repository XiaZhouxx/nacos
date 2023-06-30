package com.xz.nacos.predicate;

import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.cloud.gateway.handler.predicate.GatewayPredicate;
import org.springframework.web.server.ServerWebExchange;

import java.util.function.Predicate;

/**
 * 自定义路由匹配谓词工厂类.
 *
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
        return new GatewayPredicate() {
            @Override
            public boolean test(ServerWebExchange serverWebExchange) {
                // 自定义该次请求是否有效. 可根据config对应的配置决定.
                if (config.name.equals("xz")) {
                    return false;
                }
                return true;
            }
        };
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
