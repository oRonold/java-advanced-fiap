package br.com.fiap.relacoes.controller;

import br.com.fiap.relacoes.dto.AtualizarPostDTO;
import br.com.fiap.relacoes.dto.CadastrarPostDTO;
import br.com.fiap.relacoes.dto.DetalhesPostDTO;
import br.com.fiap.relacoes.model.DetalhesPost;
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
    public ResponseEntity<Page<DetalhesPostDTO>> listar(Pageable pageable) {
        var page = postRepository.findAll(pageable).map(DetalhesPostDTO::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesPostDTO> buscarPorId(@PathVariable Long id){
        var post = postRepository.getReferenceById(id);
        return ResponseEntity.ok().body(new DetalhesPostDTO(post));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DetalhesPostDTO> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizarPostDTO dto){
        var post = postRepository.getReferenceById(id);
        post.atualizarInformacoes(dto);
        return ResponseEntity.ok().body(new DetalhesPostDTO(post));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> excluirPost(@PathVariable Long id){
        var post = postRepository.getReferenceById(id);
        postRepository.delete(post);
        return ResponseEntity.noContent().build();
    }

}
