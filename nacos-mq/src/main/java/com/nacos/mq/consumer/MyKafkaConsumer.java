package com.nacos.mq.consumer;

import static org.apache.kafka.clients.consumer.ConsumerConfig.*;

import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.time.Duration;
import java.util.Collection;
import java.util.Collections;
import java.util.Properties;
import java.util.Set;

/**
 * remark.
 *
 * @author xz
 * @date 2023/3/16 15:08
 */
@Component
public class MyKafkaConsumer implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(MyKafkaConsumer.class);

    @Override
    public void run(String... args) throws Exception {
        new Thread(() -> {
            Properties consumerPts = new Properties();
            consumerPts.put(BOOTSTRAP_SERVERS_CONFIG, "192.168.254.110:9092,192.168.254.111:9092,192.168.254.112:9092");
            consumerPts.put(GROUP_ID_CONFIG, "local_test");
            consumerPts.put(KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
            consumerPts.put(VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");

            Set<String> topics = Collections.singleton("LOCAL-TEST");

            KafkaConsumer<String, String> consumer = new KafkaConsumer<>(consumerPts);
            consumer.subscribe(topics, new ConsumerRebalanceListener() {
                @Override
                public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
                    log.info("Rebalance before");
                }

                @Override
                public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
                    log.info("Rebalance after");
                }
            });

            try {
                while (true) {
                    final ConsumerRecords<String, String> dataList = consumer.poll(Duration.ofMillis(100L));
                    for (ConsumerRecord<String, String> data : dataList) {
                        log.info("topic: [{}], partition: [{}], offset: [{}], message content: [{}]", data.topic(), data.partition(), data.offset(), data.value());
                    }
                }
            } finally {
                consumer.close();
            }
        }).start();

    }
}
