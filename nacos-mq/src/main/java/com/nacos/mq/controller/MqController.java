package com.nacos.mq.controller;

import com.nacos.mq.producer.MyKafkaProducer;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * remark.
 *
 * @author xz
 * @date 2023/3/16 16:58
 */
@RequestMapping("/mq")
@RestController
public class MqController {

    @RequestMapping("/send/{msg}/{key}")
    public String sendMsg(@PathVariable String msg, @PathVariable String key) {
        MyKafkaProducer.sendAsync("LOCAL-TEST", key, msg);
        return "success";
    }

}
