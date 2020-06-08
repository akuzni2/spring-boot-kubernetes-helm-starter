package com.alex.demoservice.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class Producer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void send(String topic, String payload) {
        log.info("sending payload");
        kafkaTemplate.send(topic, payload);
    }

    public void send(String topic, String key, String payload) {
        kafkaTemplate.send(topic, key, payload);
    }

}
