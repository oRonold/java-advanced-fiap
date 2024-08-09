package br.com.fiap.relacoes.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record CadastrarUsuarioDTO(
        @NotBlank
        @Length(max = 50)
        String login,
        @NotBlank
        String senha) {
}
