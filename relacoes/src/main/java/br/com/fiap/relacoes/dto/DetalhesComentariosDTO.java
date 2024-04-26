package br.com.fiap.relacoes.dto;

import br.com.fiap.relacoes.model.Comentario;
import br.com.fiap.relacoes.model.DetalhesPost;
import br.com.fiap.relacoes.model.Post;

import java.time.LocalDate;

public record DetalhesComentariosDTO(Long codigo, String conteudo, String nomeAutor, LocalDate dataCriacao, DetalhesPostDTO detalhesPost) {

    public DetalhesComentariosDTO(Comentario comentario){
        this(comentario.getCodigo(), comentario.getConteudo(),
                comentario.getNomeAutor(), comentario.getDataCriacao(), new DetalhesPostDTO(comentario.getPost()));
    }

}
