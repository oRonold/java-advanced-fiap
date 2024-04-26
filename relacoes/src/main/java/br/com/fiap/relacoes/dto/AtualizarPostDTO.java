package br.com.fiap.relacoes.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record AtualizarPostDTO(
        @Size(max = 50)
        String titulo,
        @Size(max = 50)
        String autor) {
}
