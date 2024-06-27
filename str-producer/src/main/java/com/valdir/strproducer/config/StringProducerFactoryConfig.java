package com.valdir.strproducer.config;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;

@RequiredArgsConstructor // 1. cria uma instancia para injetar as properties
@Configuration
public class StringProducerFactoryConfig {

    private final KafkaProperties properties; // 1.*

    @Bean
    public ProducerFactory<String, String> producerFactory() {
        var configs = new HashMap<String, Object>();
        // adicionando as variáveis para configs declarada acima

        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, properties.getBootstrapServers());
        // pegando as propriedades de porta para o kafka

        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        // serialização de chave e valor do tópico - KEY - usando serializador já construído para uso

        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        // serialização de chave e valor do tópico - VALUE usando serializador já construído para uso

        return new DefaultKafkaProducerFactory<>(configs);
    }

    @Bean // KAFKA TEMPLATE - usa o ProducerFactory para processar os tópicos
    public KafkaTemplate<String, String> kafkaTemplate(ProducerFactory<String, String> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
        // spring(?) fica responsável por instanciar o PrFactory
    }
}
