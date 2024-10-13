package com.spring.msorder.producer;

import com.spring.msorder.events.OrderConfirmationEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class OrderProducer {

    private final KafkaTemplate<String, OrderConfirmationEvent> kafkaTemplate;

    @Autowired
    public OrderProducer(KafkaTemplate<String, OrderConfirmationEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public synchronized void sendOrderConfirmation(OrderConfirmationEvent orderConfirmationEvent){
        Message<OrderConfirmationEvent> message = MessageBuilder
                .withPayload(orderConfirmationEvent)
                .setHeader(KafkaHeaders.TOPIC,"order-confirmation-topic")
                .build();

        kafkaTemplate.send(message);
    }
}
