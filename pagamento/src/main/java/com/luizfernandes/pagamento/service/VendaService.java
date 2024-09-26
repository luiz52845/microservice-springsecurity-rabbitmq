package com.luizfernandes.pagamento.service;

import com.luizfernandes.pagamento.dto.VendaDto;
import com.luizfernandes.pagamento.enitity.ProdutoVenda;
import com.luizfernandes.pagamento.enitity.Venda;
import com.luizfernandes.pagamento.exception.ResourceNotFoundException;
import com.luizfernandes.pagamento.repository.ProdutoVendaRepository;
import com.luizfernandes.pagamento.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class VendaService {

    private final VendaRepository vendaRepository;
    private final ProdutoVendaRepository produtoVendaRepository;


    @Autowired
    public VendaService(VendaRepository vendaRepository, ProdutoVendaRepository produtoVendaRepository) {
        this.vendaRepository = vendaRepository;
        this.produtoVendaRepository = produtoVendaRepository;
    }

    public VendaDto create(VendaDto vendaDto) {
        Venda venda = vendaRepository.save(Venda.create(vendaDto));

        List<ProdutoVenda> produtosSalvos = new ArrayList<>();
        vendaDto.getProdutos().forEach(p -> {
            ProdutoVenda pv = ProdutoVenda.create(p);
            pv.setVenda(venda);
            produtosSalvos.add(produtoVendaRepository.save(pv));
        });
        venda.setProdutos(produtosSalvos);
        return VendaDto.create(venda);
    }

    public Page<VendaDto> findAll(Pageable pageable) {
        var page = vendaRepository.findAll(pageable);

        return page.map(this::converToVendaDto);
    }

    public VendaDto findById(Long id) {
        var entity = vendaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        return VendaDto.create(entity);
    }

    private VendaDto converToVendaDto(Venda produto) {
        return VendaDto.create(produto);
    }
}
