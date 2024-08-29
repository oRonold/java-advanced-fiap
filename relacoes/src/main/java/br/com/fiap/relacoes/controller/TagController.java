package br.com.fiap.relacoes.controller;

import br.com.fiap.relacoes.dto.AtualizarTagDTO;
import br.com.fiap.relacoes.dto.CadastrarTagDTO;
import br.com.fiap.relacoes.dto.DetalhesTagDTO;
import br.com.fiap.relacoes.model.blog.Tag;
import br.com.fiap.relacoes.repository.TagRepository;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/tags")
@io.swagger.v3.oas.annotations.tags.Tag(name = "Tag", description = "Operações relaciondas as Tags")
public class TagController {

    @Autowired
    private TagRepository repository;

    @PostMapping
    @Transactional
    @Operation(summary = "Cria uma Tag", description = "Cria uma Tag nova na base de dados")
    public ResponseEntity<DetalhesTagDTO> criar(@RequestBody @Valid CadastrarTagDTO dto, UriComponentsBuilder builder){
        var tag = new Tag(dto);
        repository.save(tag);
        var uri = builder.path("/{id}").buildAndExpand(tag.getCodigo()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesTagDTO(tag));
    }

    @GetMapping
    @Operation(summary = "Pesquisa Tags", description = "Pesquisa todas as Tags existentes")
    public ResponseEntity<Page<DetalhesTagDTO>> listar(Pageable pageable){
        var page = repository.findAll(pageable).map(DetalhesTagDTO::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Pesquisa por Id", description = "Exibe uma Tag pelo Id se ela existir")
    public ResponseEntity<DetalhesTagDTO> buscarPorId(@PathVariable Long id){
        var tag = repository.getReferenceById(id);
        return ResponseEntity.ok().body(new DetalhesTagDTO(tag));
    }

    @PutMapping("/{id}")
    @Transactional
    @Operation(summary = "Atualiza uma Tag", description = "Atualiza uma Tag pelo Id dela")
    public ResponseEntity<DetalhesTagDTO> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizarTagDTO dto){
        var tag = repository.getReferenceById(id);
        tag.atualizar(dto);
        return ResponseEntity.ok().body(new DetalhesTagDTO(tag));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Operation(summary = "Excluir Tag", description = "Exclui uma Tag pelo Id")
    public ResponseEntity<Void> excluir(@PathVariable Long id){
        var tag = repository.getReferenceById(id);
        repository.delete(tag);
        return ResponseEntity.noContent().build();
    }

}
