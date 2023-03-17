package com.nacos.mq.partitioner;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;

import java.util.List;
import java.util.Map;

/**
 * 分区器, 如果发送消息没有指定分区 则会通过此分区.
 *
 * @author xz
 * @date 2023/3/16 16:55
 */
public class MyKafkaPartitioner implements Partitioner {
    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        // 分区.
        // 得到topic中的分区数
        final List<PartitionInfo> partitionInfos = cluster.partitionsForTopic(topic);
        return 0;
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
