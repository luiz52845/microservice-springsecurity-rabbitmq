package com.luizfernandes.pagamento.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.luizfernandes.pagamento.enitity.ProdutoVenda;
import com.luizfernandes.pagamento.enitity.Venda;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@JsonPropertyOrder({"id", "data", "produtos", "valorTotal"})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VendaDto extends RepresentationModel<VendaDto> implements Serializable {

    private static final long serialVersionUID = 1L;


    @JsonProperty("id")
    private Long id;

    @JsonProperty("data")
    private Date data;

    @JsonProperty("produtos")
    private List<ProdutoVendaDto> produtos;

    @JsonProperty("valorTotal")
    private double valorTotal;

    public static VendaDto create(Venda venda) {
        return new ModelMapper().map(venda, VendaDto.class);
    }

}
