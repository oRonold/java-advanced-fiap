package br.com.fiap.relacoes.controller;

import br.com.fiap.relacoes.dto.CadastrarComentarioDTO;
import br.com.fiap.relacoes.dto.DetalhesComentarioDTO;
import br.com.fiap.relacoes.repository.ComentarioRepository;
import br.com.fiap.relacoes.service.ComentarioService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/comentarios")
public class ComentarioController {

    @Autowired
    private ComentarioRepository repository;

    @Autowired
    private ComentarioService service;


    @PostMapping("posts/{id}/comentarios")
    @Transactional
    public ResponseEntity<DetalhesComentarioDTO> cadastrar(@RequestBody @Valid CadastrarComentarioDTO dto, @PathVariable Long id, UriComponentsBuilder builder){
        service.cadastrar(dto, id);
        return ResponseEntity.ok().build();
    }


}
