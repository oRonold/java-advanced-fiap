package br.com.fiap.store.aula04.dto;

import br.com.fiap.store.aula04.model.EstadoProduto;
import br.com.fiap.store.aula04.model.Produto;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record DadosCadastroProdutoDTO(String nome, BigDecimal valor,
                                      Integer qtdEstoque, Boolean freteGratis,
                                      @JsonFormat(pattern = "dd/MM/yyyy")
                                      LocalDate dataFabricacao, LocalDate dataCadastro, EstadoProduto estado) {
}
