package com.valdir.strproducer.resources;

import com.valdir.strproducer.services.StringProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController // controlador rest
@RequestMapping(value = "/producer") // endopoint para acessar recursos
public class StringProducerResource {

    private final StringProducerService producerService;

    @PostMapping
    public ResponseEntity<?> sendMessage(@RequestBody String message) {
        // método para retornar response entity
        // sendMessage - recebe uma mensagem em String
        // pelo producerService.sendMessage(message);
        producerService.sendMessage(message);
        return ResponseEntity.status(HttpStatus.CREATED).build();
        // só retorna status created
    }
}
