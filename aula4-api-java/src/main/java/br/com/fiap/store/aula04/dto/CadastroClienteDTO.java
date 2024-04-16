package br.com.fiap.store.aula04.dto;

import br.com.fiap.store.aula04.model.CategoriaCliente;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record CadastroClienteDTO(
        @NotBlank(message = "Nome é obrigatório")
        String nome,
        @NotBlank
        @Size(max = 11, message = "CPF deve ter no máximo 11 numeros")
        String cpf,
        @JsonFormat(pattern = "dd/MM/yyyy")
        @Past
        @NotNull(message = "Data nascimento é obrigatória")
        LocalDate dataNascimento,
        @NotNull(message = "Categoria é obrigatória")
        CategoriaCliente categoria) {
}
