package br.com.fiap.relacoes.dto;

import br.com.fiap.relacoes.model.DetalhesPost;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CriarPostDTO(
        @NotEmpty
        String titulo,
        @NotEmpty
        String conteudo,
        @NotNull
        CriarDetalhesDTO detalhesPost) {
}
