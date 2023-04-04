package com.samieteq.rabbitmq.publisher;

import com.samieteq.rabbitmq.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQJsonProducer {

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.json.key}")
    private String routingJsonKey;

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQJsonProducer.class);

    private final RabbitTemplate template;

    public RabbitMQJsonProducer(RabbitTemplate template) {
        this.template = template;
    }

    public void sendJsonMessage(User user){
        LOGGER.info(String.format("Json message sent -> %s", user.toString()));
        template.convertAndSend(exchange, routingJsonKey, user);
    }
}
