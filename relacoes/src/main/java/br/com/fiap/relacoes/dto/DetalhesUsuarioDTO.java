package br.com.fiap.relacoes.dto;

import br.com.fiap.relacoes.model.user.Usuario;

public record DetalhesUsuarioDTO(Long id, String login, String senha) {

    public DetalhesUsuarioDTO(Usuario usuario){
        this(usuario.getId(),usuario.getLogin(),usuario.getSenha());
    }
}
