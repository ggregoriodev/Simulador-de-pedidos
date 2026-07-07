package com.ggregoriodev.notification_service.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQConsumer.class);

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String remetente;

    public RabbitMQConsumer(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @RabbitListener(queues = "${rabbitmq.queue.name}")
    public void consumirMensagem(String message) {
        LOGGER.info("Mensagem recebida: {}", message);

        try {
            SimpleMailMessage email = new SimpleMailMessage();
            email.setFrom(remetente);
            email.setTo("guilhermegregoriodossantos@gmail.com");
            email.setSubject("Novo pedido criado");
            email.setText(message);

            mailSender.send(email);
            LOGGER.info("Email enviado com sucesso!");
        } catch (Exception e) {
            LOGGER.error("Erro ao enviar email", e);
        }
    }
}