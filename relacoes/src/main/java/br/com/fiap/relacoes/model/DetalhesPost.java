package br.com.fiap.relacoes.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "EXE_JV_TB_DETALHES_POST")
public class DetalhesPost {

    @Id
    @GeneratedValue
    @Column(name = "cd_detalhes_post")
    private long codigo;

    @Column(name = "nm_autor", length = 50, nullable = false)
    private String nomeAutor;

    @Column(name = "dt_criacao", nullable = false)
    private LocalDate dataCriacao;

    @Column(name = "dt_publicacao", nullable = false)
    private LocalDate dataPublicacao;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "cd_post")
    private Post post;

}
