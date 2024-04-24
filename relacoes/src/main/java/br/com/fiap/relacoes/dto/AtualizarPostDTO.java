package br.com.fiap.relacoes.dto;

import jakarta.validation.constraints.Size;

public record AtualizarPostDTO(
        @Size(max = 50)
        String titulo,
        @Size(max = 100)
        String autor) {
}
