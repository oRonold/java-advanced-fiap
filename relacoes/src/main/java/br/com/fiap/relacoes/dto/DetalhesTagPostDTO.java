package br.com.fiap.relacoes.dto;

import br.com.fiap.relacoes.model.Post;
import br.com.fiap.relacoes.model.Tag;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public record DetalhesTagPostDTO(Long codigo, String titulo, String conteudo, String autor, LocalDate dataCriacao, LocalDate dataPublicacao, ArrayList<DetalhesTagDTO> tags) {

    public DetalhesTagPostDTO(Post post){
        this(post.getCodigo(), post.getTitulo(), post.getConteudo(), post.getDetalhesPost().getNomeAutor(),
                post.getDetalhesPost().getDataCriacao(), post.getDetalhesPost().getDataPublicacao(),
                new ArrayList<>(post.getTag().stream().map(tag -> new DetalhesTagDTO(tag.getCodigo(), tag.getNome())).collect(Collectors.toList())));
    }

}
