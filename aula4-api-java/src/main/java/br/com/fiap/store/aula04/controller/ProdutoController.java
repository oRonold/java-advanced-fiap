package br.com.fiap.store.aula04.controller;

import br.com.fiap.store.aula04.dto.AtualizarProdutoDTO;
import br.com.fiap.store.aula04.dto.DadosCadastroProdutoDTO;
import br.com.fiap.store.aula04.dto.DetalhesProdutoDTO;
import br.com.fiap.store.aula04.model.Produto;
import br.com.fiap.store.aula04.repository.ProdutoRepository;
import jakarta.persistence.Transient;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DetalhesProdutoDTO> cadastro(@RequestBody DadosCadastroProdutoDTO dto, UriComponentsBuilder builder){
        var produto = new Produto(dto);
        repository.save(produto);
        var url = builder.path("/produto/{id]").buildAndExpand(produto.getCodigo()).toUri();
        return ResponseEntity.created(url).body(new DetalhesProdutoDTO(produto));
    }

    @GetMapping
    public ResponseEntity<List<DetalhesProdutoDTO>> listarProdutos(Pageable pageable){
        var page = repository.findAll(pageable).stream().map(DetalhesProdutoDTO::new).toList();
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesProdutoDTO> buscarPorId(@PathVariable long id){
        var produto = repository.getReferenceById(id);
        return ResponseEntity.ok(new DetalhesProdutoDTO(produto));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DetalhesProdutoDTO> atualizarProduto(@RequestBody AtualizarProdutoDTO dto){
        var produto = repository.getReferenceById(dto.id());
        produto.atualizarProduto(dto);
        return ResponseEntity.ok(new DetalhesProdutoDTO(produto));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> excluirProduto(@PathVariable Long id){
        var produto = repository.getReferenceById(id);
        repository.delete(produto);
        return ResponseEntity.noContent().build();
    }
}
