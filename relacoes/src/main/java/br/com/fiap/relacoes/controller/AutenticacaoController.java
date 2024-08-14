package br.com.fiap.relacoes.controller;

import br.com.fiap.relacoes.dto.DadosAutenticacaoDTO;
import br.com.fiap.relacoes.dto.DadosTokenJwtDTO;
import br.com.fiap.relacoes.model.user.Usuario;
import br.com.fiap.relacoes.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<DadosTokenJwtDTO> login(@RequestBody DadosAutenticacaoDTO dados){
        var token = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var authentication = manager.authenticate(token);
        var tokenJwt = tokenService.gerarToken((Usuario) authentication.getPrincipal());
        return ResponseEntity.ok(new DadosTokenJwtDTO(tokenJwt));
    }
}
