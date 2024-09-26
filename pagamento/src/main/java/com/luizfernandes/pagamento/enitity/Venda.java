package com.luizfernandes.pagamento.enitity;

import com.luizfernandes.pagamento.dto.ProdutoDto;
import com.luizfernandes.pagamento.dto.VendaDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "produto_venda")
public class Venda {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @DateTimeFormat(pattern = "MM/dd/yyyy")
    @Column(name = "data", nullable = false)
    private Date data;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "venda", cascade = CascadeType.REFRESH)
    private List<ProdutoVenda> produtos;

    @Column(name = "valorTotal", nullable = false, length = 10)
    private double valorTotal;

    public static Venda create(VendaDto vendaDto) {
        return new ModelMapper().map(vendaDto, Venda.class);
    }
}
