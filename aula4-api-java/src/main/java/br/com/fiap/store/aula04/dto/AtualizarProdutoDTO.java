package br.com.fiap.store.aula04.dto;

import br.com.fiap.store.aula04.model.EstadoProduto;
import br.com.fiap.store.aula04.model.Produto;

import java.math.BigDecimal;

public record AtualizarProdutoDTO(Long id, String nome, BigDecimal valor,
                                  Integer qtdEstoque, Boolean freteGratis, EstadoProduto estado) {

}
