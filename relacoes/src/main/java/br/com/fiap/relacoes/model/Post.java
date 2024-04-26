package br.com.fiap.relacoes.model;

import br.com.fiap.relacoes.dto.AtualizarPostDTO;
import br.com.fiap.relacoes.dto.CadastrarPostDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
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

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comentario> comentario;

    @OneToOne(mappedBy = "post", cascade = CascadeType.ALL)
    private DetalhesPost detalhesPost;

    @ManyToMany
    @JoinTable(name = "EXE_JV_TB_TAG_POST",
        joinColumns = @JoinColumn(name = "cd_post"),
        inverseJoinColumns = @JoinColumn(name = "cd_tag"))
    private List<Tag> tag;

    public Post(CadastrarPostDTO dto){
        titulo = dto.titulo();
        conteudo = dto.conteudo();
        detalhesPost = new DetalhesPost(dto);
        detalhesPost.setPost(this);
    }

    public void atualizar(AtualizarPostDTO dto) {
        if(dto.titulo() != null){
            titulo = dto.titulo();
        }
        if(dto.autor() != null){
            detalhesPost.setNomeAutor(dto.autor());
        }

    }
}
