package com.luizfernandes.pagamento.enitity;


import com.luizfernandes.pagamento.dto.ProdutoVendaDto;
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
@Table(name = "produto_venda")
public class ProdutoVenda {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "id_produto", nullable = false, length = 10)
    private Long idProduto;

    @Column(name = "quantidade", nullable = true, length = 10)
    private Integer quantidade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_venda")
    private Venda venda;

    public static ProdutoVenda create(ProdutoVendaDto produtoVendaDto) {
        return new ModelMapper().map(produtoVendaDto, ProdutoVenda.class);
    }

}
