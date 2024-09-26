package com.luizfernandes.pagamento.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.luizfernandes.pagamento.enitity.ProdutoVenda;
import com.luizfernandes.pagamento.enitity.Venda;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;

@JsonPropertyOrder({"id", "idProduto", "quantidade"})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoVendaDto extends RepresentationModel<ProdutoVendaDto> implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("idProduto")
    private Long idProduto;


    @JsonProperty("quantidade")
    private Integer quantidade;

    @JsonProperty("venda")
    private Venda venda;

    public static ProdutoVendaDto create(ProdutoVenda produtoVenda) {
        return new ModelMapper().map(produtoVenda, ProdutoVendaDto.class);
    }


}
