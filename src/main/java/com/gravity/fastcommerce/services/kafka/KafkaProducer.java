package com.gravity.fastcommerce.services.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@Service
public class KafkaProducer {
    private static final Logger logger = LoggerFactory.getLogger(KafkaProducer.class);
    private final KafkaTemplate<String, String> kafkaTemplate;
    @Value("${spring.kafka.topic}")
    private String kafkaTopic;

    public KafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping("/send-message")
    public void sendMessage(String message) {
        logger.info("Inside sendMessage() method ,payload {}", message);
        kafkaTemplate.send(kafkaTopic, message);
        logger.info("Message pushed");
    }
}