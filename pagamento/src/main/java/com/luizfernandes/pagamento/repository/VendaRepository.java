package com.luizfernandes.pagamento.repository;

import com.luizfernandes.pagamento.enitity.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {
}
