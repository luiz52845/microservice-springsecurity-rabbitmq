package com.luizfernandes.pagamento.enitity;


import com.luizfernandes.pagamento.dto.ProdutoDto;
import com.luizfernandes.pagamento.dto.VendaDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "produto")
public class Produto {

    @Id
    private Long id;

    @Column(name = "estoque", nullable = false, length = 10)
    private Integer estoque;

    public static Produto create(ProdutoDto produtoDto) {
        return new ModelMapper().map(produtoDto, Produto.class);
    }
}
