package com.example.applicationservice.service;

import com.example.applicationservice.domain.EmailDetails;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQService {

    @Value("${mq.exchange}")
    private String mqExchange;

    @Value("${mq.routingKey}")
    private String mqRoutingKey;

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitMQService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(EmailDetails emailDetails){
        rabbitTemplate.convertAndSend(mqExchange, mqRoutingKey, emailDetails);

    }
}
