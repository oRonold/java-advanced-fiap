package br.com.fiap.relacoes.controller;

import br.com.fiap.relacoes.dto.AtualizarComentarioDTO;
import br.com.fiap.relacoes.dto.CadastrarComentarioDTO;
import br.com.fiap.relacoes.dto.DetalhesComentariosDTO;
import br.com.fiap.relacoes.repository.ComentarioRepository;
import br.com.fiap.relacoes.repository.PostRepository;
import br.com.fiap.relacoes.service.ComentarioService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/comentarios")
public class ComentariosController {

    @Autowired
    private ComentarioRepository comentarioRepository;

    @GetMapping
    public ResponseEntity<Page<DetalhesComentariosDTO>> paginar(Pageable pageable){
        var page = comentarioRepository.findAll(pageable).map(DetalhesComentariosDTO::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesComentariosDTO> buscarPorId(@PathVariable Long id){
        var comentario = comentarioRepository.getReferenceById(id);
        return ResponseEntity.ok().body(new DetalhesComentariosDTO(comentario));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DetalhesComentariosDTO> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizarComentarioDTO dto){
        var comentario = comentarioRepository.getReferenceById(id);
        comentario.atualizar(dto);
        return ResponseEntity.ok().body(new DetalhesComentariosDTO(comentario));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> excluir(@PathVariable Long id){
        var comentario = comentarioRepository.getReferenceById(id);
        comentarioRepository.delete(comentario);
        return ResponseEntity.noContent().build();
    }

}
