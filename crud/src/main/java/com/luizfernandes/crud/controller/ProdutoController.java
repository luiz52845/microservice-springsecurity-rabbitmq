package com.luizfernandes.crud.controller;

import com.luizfernandes.crud.dto.ProdutoDTO;
import com.luizfernandes.crud.service.ProdutoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    private final ProdutoService produtoService;
    private final PagedResourcesAssembler<ProdutoDTO> assembler;

    public ProdutoController(ProdutoService produtoService, PagedResourcesAssembler<ProdutoDTO> assembler) {
        this.produtoService = produtoService;
        this.assembler = assembler;
    }

    @GetMapping(value = "/{id}", produces = {"application/json", "application/xml", "application/x-yaml"})
    public ProdutoDTO findById(@PathVariable("id") Long id) {
        ProdutoDTO produtoDTO = produtoService.findById(id);
        produtoDTO.add(linkTo(methodOn(ProdutoController.class).findById(id)).withSelfRel());
        return produtoDTO;
    }

    @GetMapping(produces = {"application/json", "application/xml", "application/x-yaml"})
    public ResponseEntity<?> findAll(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "limit", defaultValue = "12") int limit,
            @RequestParam(value = "direction", defaultValue = "asc") String direction) {

        var sorDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, limit, Sort.by(sorDirection, "nome"));

        Page<ProdutoDTO> produtos = produtoService.findAll(pageable);
        produtos.stream()
                .forEach(p -> p.add(linkTo(methodOn(ProdutoController.class).findById(p.getId())).withSelfRel()));

        PagedModel<EntityModel<ProdutoDTO>> pagedModel = assembler.toModel(produtos);

        return new ResponseEntity<>(pagedModel, HttpStatus.OK);

    }

    @PostMapping(produces = {"application/json", "application/xml", "application/x-yaml"},
            consumes = {"application/json", "application/xml", "application/x-yaml"})
    public ProdutoDTO create(@RequestBody ProdutoDTO produtoDTO) {
        ProdutoDTO prodDto = produtoService.create(produtoDTO);
        prodDto.add(linkTo(methodOn(ProdutoController.class).findById(prodDto.getId())).withSelfRel());
        return prodDto;
    }


    @PutMapping(produces = {"application/json", "application/xml", "application/x-yaml"},
            consumes = {"application/json", "application/xml", "application/x-yaml"})
    public ProdutoDTO update(@RequestBody ProdutoDTO produtoDTO) {
        ProdutoDTO prodDto = produtoService.update(produtoDTO);
        prodDto.add(linkTo(methodOn(ProdutoController.class).findById(prodDto.getId())).withSelfRel());
        return prodDto;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        produtoService.delete(id);
        return ResponseEntity.ok().build();
    }
}
