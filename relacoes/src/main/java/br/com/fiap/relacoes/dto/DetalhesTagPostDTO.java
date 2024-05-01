package br.com.fiap.relacoes.dto;

import br.com.fiap.relacoes.model.Post;
import br.com.fiap.relacoes.model.Tag;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public record DetalhesTagPostDTO(Long codigo, String titulo, String conteudo, String autor,
                                 @JsonFormat(pattern = "dd/MM/yyyy")
                                 LocalDate dataCriacao,
                                 @JsonFormat(pattern = "dd/MM/yyyy")
                                 LocalDate dataPublicacao, ArrayList<DetalhesTagDTO> tags) {

    public DetalhesTagPostDTO(Post post){
        this(post.getCodigo(), post.getTitulo(), post.getConteudo(), post.getDetalhesPost().getNomeAutor(),
                post.getDetalhesPost().getDataCriacao(), post.getDetalhesPost().getDataPublicacao(),
                new ArrayList<>(post.getTag().stream().map(tag -> new DetalhesTagDTO(tag.getCodigo(), tag.getNome())).collect(Collectors.toList())));
    }

}
