package com.alex.demoservice.consumer;

import com.alex.demoservice.producer.Producer;
import com.jsoniter.JsonIterator;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class Consumer {

    @Autowired
    private MeterRegistry meterRegistry;

    @Autowired
    private Producer producer;

    @Value("${kafka.outgoing-topic-name}")
    private String topic;


    @KafkaListener(id = "consumer_listener",
            topics = "${kafka.incoming-topic-name}",
            containerFactory = "kafkaListenerContainerFactory")
    @Timed("consumer_batch_process")
    public void receive(@Payload List<String> messages) {
        log.info("received payload: " + messages);
    }
}
