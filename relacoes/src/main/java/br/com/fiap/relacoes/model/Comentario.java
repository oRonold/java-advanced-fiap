package br.com.fiap.relacoes.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "EXE_JV_TB_COMENTARIO")
public class Comentario {

    @Id
    @GeneratedValue
    @Column(name = "cd_comentario")
    private Long codigo;

    @Column(name = "ds_conteudo", length = 100, nullable = false)
    private String conteudo;

    @Column(name = "dt_criacao", nullable = false)
    private LocalDate dataCriacao;

    @Column(name = "nm_autor", length = 50)
    private String nomeAutor;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "cd_post", nullable = false)
    private Post post;


}
