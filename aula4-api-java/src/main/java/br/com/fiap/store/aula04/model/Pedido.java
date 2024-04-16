package br.com.fiap.store.aula04.model;

import jakarta.persistence.*;
import jakarta.persistence.criteria.Fetch;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor

@Entity
@Table(name = "aula4_pedido")
@EntityListeners(AuditingEntityListener.class)
public class Pedido {

    @Id
    @Column(name = "cd_pedido")
    private Long codigoPedido;

    @Column(name = "vl_pedido", nullable = false, precision = 9)
    private Double valorPedido;

    @Column(name = "dt_criacao")
    @CreatedDate
    private LocalDateTime dataCriacaoPedido;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "cd_cliente")
    private Cliente cliente;

    @OneToOne(mappedBy = "pedido")
    private NotaFiscal notaFiscal;

    @OneToMany(mappedBy = "pedido")
    private List<ProdutoPedido> produtos;

    @OneToMany(mappedBy = "pedido")
    private List<ProdutoPedido> produtoPedido;

}
