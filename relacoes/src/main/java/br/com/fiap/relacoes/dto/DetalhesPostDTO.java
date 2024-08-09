package br.com.fiap.relacoes.dto;

import br.com.fiap.relacoes.model.blog.Post;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record DetalhesPostDTO(Long codigo, String titulo, String conteudo, String autor,
                              @JsonFormat(pattern = "dd/MM/yyyy")
                              LocalDate dataCriacao,
                              @JsonFormat(pattern = "dd/MM/yyyy")
                              LocalDate dataPublicacao) {

    public DetalhesPostDTO(Post post){
        this(post.getCodigo(), post.getTitulo(), post.getConteudo(), post.getDetalhesPost().getNomeAutor(),
                post.getDetalhesPost().getDataCriacao(), post.getDetalhesPost().getDataPublicacao());
    }
}
