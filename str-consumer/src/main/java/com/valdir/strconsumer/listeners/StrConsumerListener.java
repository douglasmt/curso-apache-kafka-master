package com.valdir.strconsumer.listeners;

import com.valdir.strconsumer.custom.StrConsumerCustomListener;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class StrConsumerListener {

    @SneakyThrows
    @StrConsumerCustomListener(groupId = "group-1") // group-1 em create
    // group id a ser definido para grupo de consumidores
    // usa o strContainerFactory do ConcurrentKafkaListenerContainerFactory
    // em StringConsumerConfig
    public void create(String message) {
        log.info("CREATE ::: Receive message {}", message);
        throw new IllegalArgumentException("EXCEPTION...");
    }

    @StrConsumerCustomListener(groupId = "group-1") // group-1 em log
    public void log(String message) {
        log.info("LOG ::: Receive message {}", message);
    }

    // group-2 em history tem as 2 partições pois ele é de grupo diferente
    @KafkaListener(groupId = "group-2", topics = "str-topic", containerFactory = "validMessageContainerFactory")
    public void history(String message) {
        log.info("HISTORY ::: Receive message {}", message);
    }

}
