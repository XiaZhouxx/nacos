package com.nacos.mq.partitioner;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * 分区器, 如果发送消息没有指定分区 则会通过此分区.
 *
 * @author xz
 * @date 2023/3/16 16:55
 */
public class MyKafkaPartitioner implements Partitioner {
    private static final Logger log = LoggerFactory.getLogger(MyKafkaPartitioner.class);
    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        // 分区.
        // 得到topic中的分区数, 可以根据某个唯一标识计算hash值进行取余
        final List<PartitionInfo> partitionInfos = cluster.partitionsForTopic(topic);
        log.info("partitioner start, partition size : {}", partitionInfos.size());
        if (key instanceof String) {
            if ("测试消息".equals(key)) {
                log.info("测试消息分区分发");
            }
        }
        return 0;
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
