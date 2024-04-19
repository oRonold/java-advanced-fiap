package br.com.fiap.relacoes.controller;

import br.com.fiap.relacoes.dto.CadastrarPostDTO;
import br.com.fiap.relacoes.dto.DetalhesPostDTO;
import br.com.fiap.relacoes.model.Post;
import br.com.fiap.relacoes.repository.DetalhesPostRepository;
import br.com.fiap.relacoes.repository.PostRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DetalhesPostDTO> criar(@RequestBody @Valid CadastrarPostDTO dto, UriComponentsBuilder builder){
        var post = new Post(dto);
        postRepository.save(post);
        var uri = builder.path("/posts/{id}").buildAndExpand(post.getCodigo()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesPostDTO(post));
    }

    @GetMapping
    public ResponseEntity<List<DetalhesPostDTO>> listar(Pageable pageable){
        var posts = postRepository.findAll(pageable).map(DetalhesPostDTO::new).toList();
        return ResponseEntity.ok(posts);
    }

}
