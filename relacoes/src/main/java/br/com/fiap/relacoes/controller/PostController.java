package br.com.fiap.relacoes.controller;

import br.com.fiap.relacoes.dto.*;
import br.com.fiap.relacoes.model.Post;
import br.com.fiap.relacoes.repository.DetalhesPostRepository;
import br.com.fiap.relacoes.repository.PostRepository;
import br.com.fiap.relacoes.service.ComentarioService;
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

    @Autowired
    private ComentarioService service;

    @PostMapping
    @Transactional
    public ResponseEntity<DetalhesPostDTO> criar(@RequestBody @Valid CadastrarPostDTO dto, UriComponentsBuilder builder){
        var post = new Post(dto);
        postRepository.save(post);
        var uri = builder.path("/posts/{id}").buildAndExpand(post.getCodigo()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesPostDTO(post));
    }

    @PostMapping("/{id}/comentarios")
    @Transactional
    public ResponseEntity<DetalhesComentariosDTO> cadastrar(@PathVariable Long id, @RequestBody @Valid CadastrarComentarioDTO dto, UriComponentsBuilder builder){
        var comentario = service.adicionarComentario(id, dto);
        var uri = builder.path("/{id}").buildAndExpand(comentario.getCodigo()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesComentariosDTO(comentario));
    }

    @GetMapping
    public ResponseEntity<Page<DetalhesPostDTO>> listar(Pageable pageable){
        var posts = postRepository.findAll(pageable).map(DetalhesPostDTO::new);
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesPostDTO> buscarPorId(@PathVariable Long id){
        var post = postRepository.getReferenceById(id);
        return ResponseEntity.ok().body(new DetalhesPostDTO(post));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DetalhesPostDTO> atualizar(@RequestBody @Valid AtualizarPostDTO dto, @PathVariable Long id){
        var post = postRepository.getReferenceById(id);
        post.atualizar(dto);
        return ResponseEntity.ok().body(new DetalhesPostDTO(post));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> excluir(@PathVariable Long id){
        var post = postRepository.getReferenceById(id);
        postRepository.delete(post);
        return ResponseEntity.noContent().build();
    }

}
