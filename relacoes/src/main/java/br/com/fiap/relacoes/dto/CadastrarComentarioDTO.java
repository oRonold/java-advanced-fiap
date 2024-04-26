package br.com.fiap.relacoes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CadastrarComentarioDTO(
        @NotBlank @Size(max = 100)
        String conteudo,

        @NotBlank @Size(max = 50)
        String autor) {
}
