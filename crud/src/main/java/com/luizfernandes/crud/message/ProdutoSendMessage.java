package com.luizfernandes.crud.message;

import com.luizfernandes.crud.dto.ProdutoDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ProdutoSendMessage {

    @Value("${crud.rabbitmq.exchange}")
    String exchange;

    @Value("${crud.rabbitmq.routingkey}")
    String routingkey;

    public final RabbitTemplate rabbitTemplate;

    @Autowired
    public ProdutoSendMessage(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(ProdutoDTO produtoDTO) {
        rabbitTemplate.convertAndSend(exchange, routingkey, produtoDTO);

    }
}
