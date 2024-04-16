package br.com.fiap.store.aula04.model;

import br.com.fiap.store.aula04.dto.AtualizarClienteDTO;
import br.com.fiap.store.aula04.dto.CadastroClienteDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "aula4_cliente")
@EntityListeners(AuditingEntityListener.class)
public class Cliente {

    @Id
    @GeneratedValue
    @Column(name = "cd_cliente")
    private Long codigo;

    @Column(name = "nm_cliente", nullable = false, length = 100)
    private String nome;

    @Column(name = "nr_cpf", nullable = false, length = 11, unique = true)
    private String cpf;

    @Column(name = "dt_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @Column(name = "dt_cadastro")
    @CreatedDate
    private LocalDateTime dataCadastro;

    @Column(name = "ds_categoria", nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private CategoriaCliente categoria;

    @Column(name = "nr_pontos", precision = 10)
    private Integer pontos;

    @Transient
    private String token;

    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedido;

    public Cliente(CadastroClienteDTO dto){
        nome = dto.nome();
        cpf = dto.cpf();
        dataNascimento = dto.dataNascimento();
        categoria = dto.categoria();
    }

    public void atualizarInformacoes(AtualizarClienteDTO dto) {
        if(dto.nome() != null){
            this.nome = dto.nome();
        }
        if(dto.categoriaCliente() != null){
            this.categoria = dto.categoriaCliente();
        }
    }
}
