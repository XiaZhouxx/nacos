package com.xz.nacos.listener;

import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.cloud.nacos.NacosConfigProperties;
import com.alibaba.nacos.api.config.ConfigChangeEvent;
import com.alibaba.nacos.api.config.ConfigChangeItem;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.client.config.NacosConfigService;
import com.alibaba.nacos.client.config.listener.impl.AbstractConfigChangeListener;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.cloud.endpoint.event.RefreshEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Collection;
import java.util.concurrent.Executor;

/**
 * Nacos 配置刷新会触发的事件.
 *
 * @author xz
 * @date 2023/1/30 16:55
 */
@Slf4j
@Component
public class NacosConfigEnvListener  implements ApplicationListener<RefreshEvent> {
    @Resource
    NacosConfigManager configManager;
    @Resource
    NacosConfigProperties nacosConfigProperties;

    @Resource
    Environment env;

    private static final String SEP1 = "-";

    private static final String DOT = ".";

    @PostConstruct
    public void init() throws NacosException {
        String name = nacosConfigProperties.getName();

        String dataIdPrefix = nacosConfigProperties.getPrefix();
        if (StringUtils.isEmpty(dataIdPrefix)) {
            dataIdPrefix = name;
        }

        if (StringUtils.isEmpty(dataIdPrefix)) {
            dataIdPrefix = env.getProperty("spring.application.name");
        }
        String fileExtension = nacosConfigProperties.getFileExtension();
        String dataId = dataIdPrefix + DOT + fileExtension;
        configManager.getConfigService().addListener(dataId, nacosConfigProperties.getGroup(), new AbstractConfigChangeListener() {
            @Override
            public void receiveConfigChange(ConfigChangeEvent event) {
                Collection<ConfigChangeItem> items = event.getChangeItems();
                log.info("nacos config change");
                for (ConfigChangeItem item : items) {
                    log.info("key: {}, oldValue: {}, newValue: {}", item.getKey(), item.getOldValue(), item.getNewValue());
                }
            }
        });
    }

    @Override
    public void onApplicationEvent(RefreshEvent event) {
        System.out.println(event);
        System.out.println(event.getEventDesc());
    }
}
