package com.nacos.mq.interceptor;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.clients.producer.internals.ProducerInterceptors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * kafka 拦截器.
 *
 * @author xz
 * @date 2023/3/16 16:42
 */
public class MyKafkaProducerInterceptor implements ProducerInterceptor<String, String> {
    private static final Logger log = LoggerFactory.getLogger(MyKafkaProducerInterceptor.class);

    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> record) {
        log.info("on send");
        return record;
    }

    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {
        log.info("kafka ack");
    }

    @Override
    public void close() {
        log.info("kafka close");
    }

    @Override
    public void configure(Map<String, ?> configs) {
        log.info("kafka config");
    }
}
