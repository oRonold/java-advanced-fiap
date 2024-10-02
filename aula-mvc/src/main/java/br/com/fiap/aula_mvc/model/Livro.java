package br.com.fiap.aula_mvc.model;

import jakarta.persistence.*;
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

    @Column(name = "ds_livro", nullable = false)
    private String titulo;

    @Column(name = "ds_sinopse", nullable = false)
    private String sinopse;

    @Column(name = "vl_livro", nullable = false, precision = 5, scale = 2)
    private BigDecimal preco;

    @Column(name = "nr_paginas", nullable = false, precision = 4)
    private Integer paginas;

    @Column(name = "nr_isbn", nullable = false, unique = true, length = 60)
    private String isbn;

    @Column(name = "st_livro", nullable = false)
    private Boolean disponivel;

    @Column(name = "dt_publicacao", nullable = false)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataPublicacao;

    @Enumerated(EnumType.STRING)
    @Column(name = "ds_genero", nullable = false)
    private Genero genero;
}
