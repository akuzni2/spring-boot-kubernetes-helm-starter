package com.alex.demoservice;

import com.alex.demoservice.consumer.Consumer;
import com.alex.demoservice.producer.Producer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Value("${kafka.incoming-topic-name}")
    private String INCOMING_TOPIC;

    @Autowired
    private Consumer consumer;

    @Autowired
    private Producer producer;


    @Override
    public void run(String... args) {

    }
}
