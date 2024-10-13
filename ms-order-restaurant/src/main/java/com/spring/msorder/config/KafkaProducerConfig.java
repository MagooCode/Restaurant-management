package com.spring.msorder.config;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaProducerConfig {
    @Bean
    public NewTopic createOrderConfirmationTopic(){
        return new NewTopic("order-confirmation-topic", 2, (short) 1);
    }

}
