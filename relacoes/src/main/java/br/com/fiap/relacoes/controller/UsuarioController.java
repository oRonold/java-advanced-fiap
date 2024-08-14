package br.com.fiap.relacoes.controller;

import br.com.fiap.relacoes.dto.CadastrarUsuarioDTO;
import br.com.fiap.relacoes.dto.DetalhesUsuarioDTO;
import br.com.fiap.relacoes.model.user.Usuario;
import br.com.fiap.relacoes.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    @Transactional
    public ResponseEntity<DetalhesUsuarioDTO> cadastrar(@RequestBody @Valid CadastrarUsuarioDTO dto, UriComponentsBuilder builder){
        var usuario = new Usuario(dto, passwordEncoder.encode(dto.senha()));
        usuarioRepository.save(usuario);
        var uri = builder.path("/usuario/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesUsuarioDTO(usuario));
    }
}
