package br.com.fiap.relacoes.model;

import br.com.fiap.relacoes.dto.CriarPostDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "EXE_JV_TB_POST")
public class Post {

    @Id
    @GeneratedValue
    @Column(name = "cd_post")
    private Long codigo;

    @Column(name = "ds_titulo", length = 50, nullable = false)
    private String titulo;

    @Column(name = "ds_conteudo", length = 500, nullable = false)
    private String conteudo;

    @OneToMany(mappedBy = "post")
    private List<Comentario> comentario;

    @OneToOne(mappedBy = "post", cascade = CascadeType.PERSIST)
    private DetalhesPost detalhesPost;

    @ManyToMany
    @JoinTable(name = "EXE_JV_TB_TAG_POST",
        joinColumns = @JoinColumn(name = "cd_post"),
        inverseJoinColumns = @JoinColumn(name = "cd_tag"))
    private List<Tag> tag;

    public Post(CriarPostDTO dto){
        this.titulo = dto.titulo();
        this.conteudo = dto.conteudo();
        this.detalhesPost = new DetalhesPost(dto.detalhesPost());
    }

}
