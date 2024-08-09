package br.com.fiap.relacoes.dto;

import br.com.fiap.relacoes.model.blog.Comentario;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record DetalhesComentariosDTO(Long codigo, String conteudo, String autor,
                                     @JsonFormat(pattern = "dd/MM/yyyy")
                                     LocalDate dataCriacao, DetalhesPostDTO detalhesPost) {

    public DetalhesComentariosDTO(Comentario comentario){
        this(comentario.getCodigo(), comentario.getConteudo(),
                comentario.getNomeAutor(), comentario.getDataCriacao(), new DetalhesPostDTO(comentario.getPost()));
    }

}
