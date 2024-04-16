package br.com.fiap.store.aula04.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Getter
@NoArgsConstructor

@Entity
@Table(name = "aula4_produto_pedido")
public class ProdutoPedido {

    @Id
    @Column(name = "cd_produto_pedido")
    private Long codigoProdutoPedido;

    @Column(name = "vl_produto_pedido", nullable = false, precision = 9, scale = 2)
    private BigDecimal valorProdutoPedido;

    @Column(name = "qtd_produto_pedido", nullable = false)
    private Integer qtdProdutoPedido;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "cd_pedido")
    private Pedido pedido;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "cd_produto")
    private Produto produto;

}
