package br.com.fiap.relacoes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record CadastrarComentarioDTO(
        @NotEmpty
        @Size(max = 500)
        String conteudo,
        @NotEmpty
        @Size(max = 100)
        String autor) {
}
