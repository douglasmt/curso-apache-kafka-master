package com.valdir.strproducer.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Log4j2 // para usar o log.info
@RequiredArgsConstructor
@Service // 1.*
public class StringProducerService {
    // 1. usado para injetar instancias do STRING producer service

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message) { // MÉTODO SEM RETORNO
        log.info("Send message - (log doug) {}", message);
        kafkaTemplate.send("str-topic", message).addCallback(
                success -> {
                    if(success != null) {
                        log.info("Send message with success {}", message);
                        log.info("Partition {}, Offset {}",
                                success.getRecordMetadata().partition(),
                                success.getRecordMetadata().offset());
                    }
                },
                error -> log.error("Error send message")
        );
    }
}
