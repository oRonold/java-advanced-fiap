package br.com.fiap.store.aula04.dto;

import br.com.fiap.store.aula04.model.CategoriaCliente;
import br.com.fiap.store.aula04.model.Cliente;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record DadosCadatroClienteDTO(Long id, String nome, String cpf, LocalDate dataNascimento, CategoriaCliente categoria, LocalDateTime dataCadastro) {

    public DadosCadatroClienteDTO(Cliente cliente){
        this(cliente.getCodigo(), cliente.getNome(), cliente.getCpf(), cliente.getDataNascimento(), cliente.getCategoria(), cliente.getDataCadastro());
    }

}
