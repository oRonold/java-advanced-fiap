package br.com.fiap.store.aula04.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor

@Entity
@Table(name = "aula4_fornecedor")
public class Fornecedor {

    @Id
    @Column(name = "cd_fornecedor")
    private Long codigoFornecedor;

    @Column(name = "nm_fornecedor", nullable = false, length = 50)
    private String nomeFornecedor;

    @Column(name = "nr_cnpj", nullable = false, length = 22, unique = true)
    private String cnpjFornecedor;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "aula4_fornecedor_produto",
    joinColumns = @JoinColumn(name = "cd_fornecedor"),
    inverseJoinColumns = @JoinColumn(name = "cd_produto"))
    private List<Produto> produto;

}
