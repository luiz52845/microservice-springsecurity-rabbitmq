package com.luizfernandes.crud.service;

import com.luizfernandes.crud.dto.ProdutoDTO;
import com.luizfernandes.crud.entity.Produto;
import com.luizfernandes.crud.exception.ResourceNotFoundException;
import com.luizfernandes.crud.message.ProdutoSendMessage;
import com.luizfernandes.crud.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.function.Function;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final ProdutoSendMessage produtoSendMessage;


    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository, ProdutoSendMessage produtoSendMessage) {
        this.produtoRepository = produtoRepository;
        this.produtoSendMessage = produtoSendMessage;
    }

    public ProdutoDTO create(ProdutoDTO produtoDTO) {
        ProdutoDTO produtoDTOResponse = ProdutoDTO.create(produtoRepository.save(Produto.create(produtoDTO)));
        produtoSendMessage.sendMessage(produtoDTOResponse);
        return produtoDTOResponse;
    }

    public Page<ProdutoDTO> findAll(Pageable pageable) {
        var page = produtoRepository.findAll(pageable);

        return page.map(this::converToProdutoDTO);
    }

    private ProdutoDTO converToProdutoDTO(Produto produto) {
        return ProdutoDTO.create(produto);
    }

    public ProdutoDTO findById(Long id) {
        var entity = produtoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        return ProdutoDTO.create(entity);
    }

    public ProdutoDTO update(ProdutoDTO produtoDTO) {
        final Optional<Produto> optionalProduto = produtoRepository.findById(produtoDTO.getId());

        if (!optionalProduto.isPresent()) {
            new ResourceNotFoundException("No records found this ID");
        }

        return ProdutoDTO.create(produtoRepository.save(Produto.create(produtoDTO)));

    }

    public void delete(Long id) {
        var entity = produtoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        produtoRepository.delete(entity);
    }

}
