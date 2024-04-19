package br.com.fiap.relacoes.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record CadastrarPostDTO(
        @NotBlank @Size(max = 50)
        String titulo,

        @NotBlank @Size(max = 500)
        String conteudo,

        @NotBlank @Size(max = 50)
        String autor,

        @NotNull @Future
        LocalDate dataPublicacao) {
}
