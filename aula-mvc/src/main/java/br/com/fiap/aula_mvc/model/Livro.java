package br.com.fiap.aula_mvc.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter @Setter @NoArgsConstructor
public class Livro {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message = "Título obrigatório")
    @Column(name = "ds_livro", nullable = false)
    private String titulo;

    @NotBlank(message = "Sinopse obrigátoria")
    @Column(name = "ds_sinopse", nullable = false)
    private String sinopse;

    @Min(value = 0, message = "Preço não pode ser negativo")
    @NotNull(message = "Preço não pode ser nulo")
    @Column(name = "vl_livro", nullable = false, precision = 5, scale = 2)
    private BigDecimal preco;

    @Min(value = 1, message = "O livro tem que possuir pelo menos uma página")
    @NotNull(message = "Pagina não pode ser nulo")
    @Column(name = "nr_paginas", nullable = false, precision = 4)
    private Integer paginas;

    @Size(max = 13, message = "O ISBN precisa conter 13 dígitos")
    @Column(name = "nr_isbn", unique = true, length = 60)
    private String isbn;

    @Column(name = "st_livro", nullable = false)
    private Boolean disponivel;

    @Past
    @Column(name = "dt_publicacao", nullable = false)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataPublicacao;

    @Enumerated(EnumType.STRING)
    @Column(name = "ds_genero", nullable = false)
    private Genero genero;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "cd_editora", nullable = false)
    private Editora editora;
}
