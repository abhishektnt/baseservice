package com.gravity.fastcommerce.controllers.kafka;

import com.gravity.fastcommerce.services.kafka.KafkaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/kafka/producer")
public class KafkaController {
    private static final Logger logger = LoggerFactory.getLogger(KafkaController.class);
    private final KafkaProducer kafkaProducer;

    public KafkaController(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @PostMapping("/send-message")
    public ResponseEntity<String> sendMessage(@RequestParam("message") String message) {
        logger.info("Entering sendMessage method with message: {}", message);
        this.kafkaProducer.sendMessage(message);
        logger.info("Exiting sendMessage method");
        return ResponseEntity.ok("Message sent successfully!");
    }
}