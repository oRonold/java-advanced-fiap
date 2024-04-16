package br.com.fiap.store.aula04.model;

import br.com.fiap.store.aula04.dto.AtualizarProdutoDTO;
import br.com.fiap.store.aula04.dto.DadosCadastroProdutoDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "aula4_produto")
@EntityListeners(AuditingEntityListener.class)
public class Produto {

    @Id
    @GeneratedValue
    @Column(name = "cd_produto")
    private Long codigo;

    @Column(name = "nm_produto", length = 50, nullable = false)
    private String nome;

    @Column(name = "vl_produto", scale = 2, precision = 9, nullable = false)
    private BigDecimal valor;

    @Transient
    private Double precoComDesconto;

    @Column(name = "nr_estoque", precision = 9)
    private Integer qtdEstoque;

    @Column(name = "st_frete_gratis", length = 1)
    private Boolean freteGratis;

    @Column(name = "dt_fabricacao")
    @JsonFormat(pattern = "dd/MM/yyyyy")
    private LocalDate dataFabricacao;

    @Column(name = "dt_cadastro")
    @CreatedDate
    private LocalDate dataCadastro;

    @Column(name = "ds_estado", length = 30, nullable = false)
    @Enumerated(EnumType.STRING)
    private EstadoProduto estado;

    @ManyToMany(mappedBy = "produto")
    private List<Fornecedor> forcedor;

    @OneToMany(mappedBy = "pedido")
    private List<ProdutoPedido> pedido;

    @OneToMany(mappedBy = "produto")
    private List<ProdutoPedido> produtoPedido;

    public Produto(DadosCadastroProdutoDTO dto){
        nome = dto.nome();
        valor = dto.valor();
        qtdEstoque = dto.qtdEstoque();
        freteGratis = dto.freteGratis();
        dataCadastro = dto.dataFabricacao();
        dataFabricacao = dto.dataFabricacao();
        estado = dto.estado();
    }

    public void atualizarProduto(AtualizarProdutoDTO dto){
        if(dto.nome() != null) {
            nome = dto.nome();
        }
        if(dto.valor() != null){
            valor = dto.valor();
        }
        if(dto.qtdEstoque() != null){
            qtdEstoque = dto.qtdEstoque();
        }
        if(dto.freteGratis() != null){
            freteGratis = dto.freteGratis();
        }
        if(dto.estado() != null){
            estado = dto.estado();
        }
    }

}
