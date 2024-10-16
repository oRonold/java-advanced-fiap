package br.com.fiap.aula_mvc.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor
public class Editora {

    @Id
    @GeneratedValue
    @Column(name = "cd_editora")
    private Long id;

    @NotBlank(message = "O nome da editora não pode estar vazia")
    @Column(name = "nm_editora", nullable = false)
    private String nome;

    @NotBlank(message = "CNPJ não pode estar vazio")
    @Column(name = "nr_cnpj", nullable = false, unique = true, length = 25)
    private String cnpj;

    @NotBlank(message = "Email não pode estar vazio")
    @Email
    @Column(name = "ds_email", unique = true)
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cd_endereco", nullable = false)
    private Endereco endereco;

    @OneToMany(mappedBy = "editora")
    private List<Livro> livros;
}
