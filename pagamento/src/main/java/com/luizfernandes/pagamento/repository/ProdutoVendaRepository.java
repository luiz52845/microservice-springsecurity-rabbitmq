package com.luizfernandes.pagamento.repository;

import com.luizfernandes.pagamento.enitity.ProdutoVenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoVendaRepository extends JpaRepository<ProdutoVenda, Long> {
}
