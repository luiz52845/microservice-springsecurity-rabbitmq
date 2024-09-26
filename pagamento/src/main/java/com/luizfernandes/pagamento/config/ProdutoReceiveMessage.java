package com.luizfernandes.pagamento.config;

import com.luizfernandes.pagamento.dto.ProdutoDto;
import com.luizfernandes.pagamento.enitity.Produto;
import com.luizfernandes.pagamento.repository.ProdutoRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class ProdutoReceiveMessage {
    private ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoReceiveMessage(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @RabbitListener(queues = {"${crud.rabbitmq.queue}"})
    public void receive(@Payload ProdutoDto produtoDto) {
        produtoRepository.save(Produto.create(produtoDto));

    }
}
