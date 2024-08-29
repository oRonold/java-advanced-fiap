package br.com.fiap.relacoes.controller;

import br.com.fiap.relacoes.dto.CadastrarUsuarioDTO;
import br.com.fiap.relacoes.dto.DetalhesUsuarioDTO;
import br.com.fiap.relacoes.model.user.Usuario;
import br.com.fiap.relacoes.repository.UsuarioRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/usuario")
@Tag(name = "Usuario", description = "Operações relacionadas ao cadastro e login do usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    @Transactional
    @Operation(summary = "Cadastra usuário", description = "Cadastro um usuário na base de dados")
    public ResponseEntity<DetalhesUsuarioDTO> cadastrar(@RequestBody @Valid CadastrarUsuarioDTO dto, UriComponentsBuilder builder){
        var usuario = new Usuario(dto, passwordEncoder.encode(dto.senha()));
        usuarioRepository.save(usuario);
        var uri = builder.path("/usuario/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesUsuarioDTO(usuario));
    }

    @GetMapping
    @Operation(summary = "Busca usuários", description = "Retorna todos os usuários da base de dados")
    public ResponseEntity<Page<DetalhesUsuarioDTO>> buscar(Pageable pageable){
        var page = usuarioRepository.findAll(pageable).map(DetalhesUsuarioDTO::new);
        return ResponseEntity.ok(page);
    }
}
