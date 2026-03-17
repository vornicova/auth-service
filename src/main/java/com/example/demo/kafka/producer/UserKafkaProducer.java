package com.example.demo.kafka.producer;


import com.example.demo.kafka.event.UserCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserKafkaProducer {

    private final KafkaTemplate<String, UserCreatedEvent> kafkaTemplate;

    public void sendUserCreated(UserCreatedEvent event) {
        kafkaTemplate.send("user-created", event.getEmail(), event);
    }
}
