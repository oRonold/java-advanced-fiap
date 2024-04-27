package br.com.fiap.relacoes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AtualizarTagDTO(
        @Size(max = 20)
        String nome) {
}
