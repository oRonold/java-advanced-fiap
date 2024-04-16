package br.com.fiap.store.aula04.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor

@Entity
@Table(name = "aula4_nota_fiscal")
@EntityListeners(AuditingEntityListener.class)
public class NotaFiscal {

    @Id
    @Column(name = "cd_nota_fiscal")
    private Long codigoNotaFiscal;

    @Column(name = "vl_nota_fiscal", precision = 9, scale = 2, nullable = false)
    private BigDecimal valorNotaFiscal;

    @Column(name = "dt_nota_fiscal")
    @CreatedDate
    private LocalDateTime dataNotaFiscal;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "cd_pedido")
    private Pedido pedido;

}
