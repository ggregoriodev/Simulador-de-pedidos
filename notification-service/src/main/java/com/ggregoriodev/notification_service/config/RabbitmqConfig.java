package com.ggregoriodev.notification_service.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {

    @Value("${rabbitmq.queue.name}")
    private String queue;

    @Bean
    public Queue emailQueue() {
        return new Queue(queue);
    }
}