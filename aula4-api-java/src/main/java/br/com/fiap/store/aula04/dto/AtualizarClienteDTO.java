package br.com.fiap.store.aula04.dto;

import br.com.fiap.store.aula04.model.CategoriaCliente;
import br.com.fiap.store.aula04.model.Cliente;

public record AtualizarClienteDTO(Long id, String nome, CategoriaCliente categoriaCliente) {

}
