package com.xz.nacos.config;

import com.alibaba.cloud.nacos.NacosServiceManager;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.alibaba.nacos.client.naming.NacosNamingService;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Properties;

/**
 * @author xz
 * @ClassName MyNacosDiscoveryProperties
 * @Description
 * @date 2020/11/6 0006 22:48
 **/
@ConfigurationProperties("spring.cloud.nacos.discovery")
public class MyNacosDiscoveryProperties extends NacosServiceManager {
    private Boolean ephemeral = true;

    public Boolean getEphemeral() {
        return ephemeral;
    }

    public void setEphemeral(Boolean ephemeral) {
        this.ephemeral = ephemeral;
    }

    @Override
    public NamingService getNamingService(Properties properties) {
        try {
            return new NacosNamingService(properties){
                @Override
                public void registerInstance(String serviceName, String groupName, Instance instance) throws NacosException {
                    if (ephemeral != null) {
                        instance.setEphemeral(ephemeral);
                    }
                    super.registerInstance(serviceName, groupName, instance);
                }
            };
        } catch (NacosException e) {
            e.printStackTrace();
        }
        return super.getNamingService(properties);
    }

//    @Override
//    public NamingService namingServiceInstance() {
//        Properties properties = new Properties();
//        properties.put(SERVER_ADDR, super.getServerAddr());
//        properties.put(NAMESPACE, super.getNamespace());
//        properties.put(UtilAndComs.NACOS_NAMING_LOG_NAME, super.getLogName());
//        properties.put(ENDPOINT, super.getEndpoint());
//        properties.put(ACCESS_KEY, super.getAccessKey());
//        properties.put(SECRET_KEY, super.getSecretKey());
//        properties.put(CLUSTER_NAME, super.getClusterName());
//        properties.put(NAMING_LOAD_CACHE_AT_START, super.getNamingLoadCacheAtStart());
//        NacosNamingService service = null;
//        try {
//            service = new NacosNamingService(properties) {
//                @Override
//                public void registerInstance(String serviceName, Instance instance) throws NacosException {
//                    instance.setEphemeral(ephemeral);
//                    super.registerInstance(serviceName, instance);
//                }
//            };
//        } catch (NacosException e) {
//            e.printStackTrace();
//        }
//        return service;
//    }
}
