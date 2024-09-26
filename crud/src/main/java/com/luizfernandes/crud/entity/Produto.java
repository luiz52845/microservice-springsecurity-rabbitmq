package com.luizfernandes.crud.entity;

import com.luizfernandes.crud.dto.ProdutoDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nome", nullable = false, length = 255)
    private String nome;

    @Column(name = "estoque", nullable = false, length = 10)
    private Integer estoque;

    @Column(name = "preco", nullable = false, length = 10)
    private Double preco;

    public static  Produto create (ProdutoDTO produtoDTO){
        return new ModelMapper().map(produtoDTO, Produto.class);
    }
}
