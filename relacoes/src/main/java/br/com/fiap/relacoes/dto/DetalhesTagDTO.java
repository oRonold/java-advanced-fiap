package br.com.fiap.relacoes.dto;

import br.com.fiap.relacoes.model.blog.Tag;

public record DetalhesTagDTO(Long codigo, String nome) {

    public DetalhesTagDTO(Tag tag){
        this(tag.getCodigo(), tag.getNome());
    }

}
