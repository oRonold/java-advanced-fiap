package br.com.fiap.relacoes.dto;

import jakarta.validation.constraints.Size;

public record AtualizarComentarioDTO(
        @Size(max = 200)
        String conteudo,
        @Size(max = 50)
        String autor) {
}
