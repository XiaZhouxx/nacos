package com.nacos.mq.producer;

import static org.apache.kafka.clients.producer.ProducerConfig.*;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Properties;
import java.util.concurrent.locks.LockSupport;

/**
 * remark.
 *
 * @author xz
 * @date 2023/3/16 15:09
 */
@Component
public class MyKafkaProducer {
    @Value("${kafka.servers.url:192.168.132.136:9092}")
    String kafkaServersUrl;

    private static final Logger log = LoggerFactory.getLogger(MyKafkaProducer.class);
    static KafkaProducer<String, String> producer;
    @PostConstruct
    public void init() {
        Properties productPts = new Properties();
        productPts.put(BOOTSTRAP_SERVERS_CONFIG, kafkaServersUrl);
        productPts.put(KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        productPts.put(VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        // kafka拦截器链, 多个逗号隔开
        productPts.put(INTERCEPTOR_CLASSES_CONFIG, "com.nacos.mq.interceptor.MyKafkaProducerInterceptor");
        // 自定义分区器
        productPts.put(PARTITIONER_CLASS_CONFIG, "com.nacos.mq.partitioner.MyKafkaPartitioner");
        producer = new KafkaProducer<>(productPts);
    }

    public static void sendAsync(String topic, String key, String msg) {
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, msg);
        producer.send(record, (metadata, exception) -> {
            if (exception != null) {
                log.error("send kafka message fail, error : {}", exception.getMessage());
                exception.printStackTrace();
            }
        });
    }

}
