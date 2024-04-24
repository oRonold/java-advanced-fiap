package br.com.fiap.relacoes.dto;

import br.com.fiap.relacoes.model.Comentario;
import br.com.fiap.relacoes.model.Post;

import java.time.LocalDate;

public record DetalhesComentarioDTO(String conteudo, String autor, LocalDate dataCriacao, DetalhesPostDTO dto) {

}
