package com.gravity.fastcommerce.services.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.kafka.annotation.KafkaListener;

@Service
public class KafkaConsumer {
    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);
    @KafkaListener(topics = "${spring.kafka.topic}", groupId = "${spring.kafka.consumer.group-id}")
    //@KafkaListener(topics = "gravity", groupId = "group_id")
    public void listen(ConsumerRecord<String, String> record) {
        logger.info("Inside consumer listener , payload {}", record.toString());
        logger.debug("Received message:, {}" , record.value());
    }
}