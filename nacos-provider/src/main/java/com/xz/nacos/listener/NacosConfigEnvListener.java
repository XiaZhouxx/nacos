package com.xz.nacos.listener;

import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.cloud.nacos.NacosConfigProperties;
import com.alibaba.nacos.api.config.ConfigChangeEvent;
import com.alibaba.nacos.api.config.ConfigChangeItem;
import com.alibaba.nacos.api.config.ConfigService;
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
    /**
     * 环境后缀分割
     */
    private static final String SEP1 = "-";

    private static final String DOT = ".";

    @PostConstruct
    public void init() throws NacosException {
        String name = nacosConfigProperties.getName();
        ConfigService service = configManager.getConfigService();


        String dataIdPrefix = nacosConfigProperties.getPrefix();
        if (StringUtils.isEmpty(dataIdPrefix)) {
            dataIdPrefix = name;
        }

        if (StringUtils.isEmpty(dataIdPrefix)) {
            dataIdPrefix = env.getProperty("spring.application.name");
        }

        String fileExtension = nacosConfigProperties.getFileExtension();
        // String profile = env.getActiveProfiles()[0];
        // String dataId = dataIdPrefix + SPE1 + profile + DOT + fileExtension;
        String dataId = dataIdPrefix + DOT + fileExtension;
        // 这里只能拿到的变更元素的key 新值 旧值
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
        // 这里拿到的是全量配置
        configManager.getConfigService().addListener(dataId, nacosConfigProperties.getGroup(), new Listener() {
            @Override
            public Executor getExecutor() {
                return null;
            }

            @Override
            public void receiveConfigInfo(String configInfo) {
                // 可以基于全量配置拿到想要的配置集, YamlUtil.load(new StringReader(configInfo)), 如果是json就更好处理了, JSONObject.parse(configInfo);
                log.info(configInfo);
            }
        });
    }

    @Override
    public void onApplicationEvent(RefreshEvent event) {
        System.out.println(event.getEventDesc());
    }
}
