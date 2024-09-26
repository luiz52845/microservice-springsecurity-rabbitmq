package com.luizfernandes.crud.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.luizfernandes.crud.entity.Produto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;


@JsonPropertyOrder({"id", "nome", "estoque", "preco"})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDTO extends RepresentationModel<ProdutoDTO>implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("nome")
    private String nome;

    @JsonProperty("estoque")
    private Integer estoque;

    @JsonProperty("preco")
    private Double preco;

    public static ProdutoDTO create (Produto produto){
        return new ModelMapper().map(produto, ProdutoDTO.class);
    }
}
