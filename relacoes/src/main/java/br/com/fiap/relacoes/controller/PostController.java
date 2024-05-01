package br.com.fiap.relacoes.controller;

import br.com.fiap.relacoes.dto.*;
import br.com.fiap.relacoes.model.Post;
import br.com.fiap.relacoes.model.Tag;
import br.com.fiap.relacoes.repository.PostRepository;
import br.com.fiap.relacoes.repository.TagRepository;
import br.com.fiap.relacoes.service.ComentarioService;
import br.com.fiap.relacoes.service.TagService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private ComentarioService service;

    @Autowired
    private TagService tagService;

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
    public ResponseEntity<DetalhesComentariosDTO> adicionarComentario(@PathVariable Long id, @RequestBody @Valid CadastrarComentarioDTO dto, UriComponentsBuilder builder){
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

    @PutMapping("/{post}/tags/{tag}")
    @Transactional
    public ResponseEntity<DetalhesTagPostDTO> adicionarTag(@PathVariable("post") Long postId, @PathVariable("tag") Long tagId){
        var post = tagService.adicionarTagAoPost(postId, tagId);
        return ResponseEntity.ok().body(new DetalhesTagPostDTO(post));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> excluir(@PathVariable Long id){
        var post = postRepository.getReferenceById(id);
        postRepository.delete(post);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{idPost}/tags/{idTag}")
    @Transactional
    public ResponseEntity<Void> removerTag(@PathVariable("idPost") Long idPost, @PathVariable("idTag") Long idTag){
        var post = postRepository.getReferenceById(idPost);
        var tag = tagRepository.getReferenceById(idTag);
        post.getTag().remove(tag);
        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/{id}/tags")
    @Transactional
    public ResponseEntity<Void> removerTodasTags(@PathVariable Long id){
        var post = postRepository.getReferenceById(id);
        post.getTag().removeAll(post.getTag());
        return ResponseEntity.noContent().build();
    }

}
