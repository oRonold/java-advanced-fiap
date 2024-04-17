package br.com.fiap.relacoes.controller;

import br.com.fiap.relacoes.dto.CriarPostDTO;
import br.com.fiap.relacoes.dto.DetalhesPostDTO;
import br.com.fiap.relacoes.model.Post;
import br.com.fiap.relacoes.repository.PostRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DetalhesPostDTO> criar(@RequestBody @Valid CriarPostDTO dto, UriComponentsBuilder builder){
        var post = new Post(dto);
        repository.save(post);
        var uri = builder.path("/{id}").buildAndExpand(post.getCodigo()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesPostDTO(post));
    }

    @GetMapping
    public ResponseEntity<Page<DetalhesPostDTO>> listar(Pageable pageable){
        var page = repository.findAll(pageable).map(DetalhesPostDTO::new);
        return ResponseEntity.ok(page);
    }

}
