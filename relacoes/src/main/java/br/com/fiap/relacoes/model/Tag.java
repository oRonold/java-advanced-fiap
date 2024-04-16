package br.com.fiap.relacoes.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "EXE_JV_TB_TAG")
public class Tag {

    @Id
    @GeneratedValue
    @Column(name = "cd_tag")
    private Long codigo;

    @Column(name = "nm_tag", length = 20, nullable = false)
    private String nome;

    @ManyToMany(mappedBy = "tag")
    private List<Post> post;

}
