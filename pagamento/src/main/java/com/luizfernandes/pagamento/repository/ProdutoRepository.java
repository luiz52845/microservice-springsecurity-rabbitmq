package com.luizfernandes.pagamento.repository;

import com.luizfernandes.pagamento.enitity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
