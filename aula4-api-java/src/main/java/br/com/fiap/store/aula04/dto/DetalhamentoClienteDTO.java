package br.com.fiap.store.aula04.dto;

import br.com.fiap.store.aula04.model.CategoriaCliente;
import br.com.fiap.store.aula04.model.Cliente;

import java.time.LocalDate;

public record DetalhamentoClienteDTO(Long id, String nome, String cpf, LocalDate dataNascimento, CategoriaCliente categoria) {

    public DetalhamentoClienteDTO(Cliente cliente){
        this(cliente.getCodigo(), cliente.getNome(), cliente.getCpf(), cliente.getDataNascimento(), cliente.getCategoria());
    }

}
