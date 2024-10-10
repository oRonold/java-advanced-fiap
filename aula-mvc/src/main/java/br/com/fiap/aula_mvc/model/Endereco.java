package br.com.fiap.aula_mvc.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Entity
@Getter @Setter @NoArgsConstructor
public class Endereco {

    @Id
    @GeneratedValue
    @Column(name = "cd_endereco")
    private Long id;

    @NotBlank(message = "Logradouro não pode estar vazio")
    @Column(name = "ds_logradouro", nullable = false)
    private String logradouro;

    @NotBlank(message = "CEP não pode estar vazio")
    @Length(max = 11)
    @Column(name = "nr_cep", nullable = false)
    private String cep;

    @NotBlank(message = "O número não pode estar vazio")
    @Column(name = "ds_numero", nullable = false)
    private String numero;

    @OneToOne(mappedBy = "endereco")
    private Editora editora;
}
