package br.com.fiap.store.aula04.dto;

import br.com.fiap.store.aula04.model.EstadoProduto;
import br.com.fiap.store.aula04.model.Produto;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DetalhesProdutoDTO(Long id, String nome, BigDecimal valor, Integer QtdEstoque, Boolean freteGratis,
                                 @JsonFormat(pattern = "dd/MM/yyyy")
                                 LocalDate dataFabricacao, LocalDate dataCadastro, EstadoProduto estado) {

    public DetalhesProdutoDTO(Produto produto) {
        this(produto.getCodigo(), produto.getNome(), produto.getValor(), produto.getQtdEstoque(), produto.getFreteGratis(),
                produto.getDataFabricacao(), produto.getDataCadastro(), produto.getEstado());
    }
}
