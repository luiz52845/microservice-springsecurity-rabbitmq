package com.luizfernandes.pagamento.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.luizfernandes.pagamento.enitity.Produto;
import com.luizfernandes.pagamento.enitity.Venda;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;

@JsonPropertyOrder({"id", "estoque"})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDto extends RepresentationModel<VendaDto> implements Serializable {


    private static final long serialVersionUID = 1L;


    @JsonProperty("id")
    private Long id;

    @JsonProperty("estoque")
    private Integer estoque;

    public static ProdutoDto create(Produto produto) {
        return new ModelMapper().map(produto, ProdutoDto.class);
    }


}