package br.com.fiap.relacoes.model;

import br.com.fiap.relacoes.dto.CadastrarPostDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "EXE_JV_TB_DETALHES_POST")
@EntityListeners(AuditingEntityListener.class)
public class DetalhesPost {

    @Id
    @GeneratedValue
    @Column(name = "cd_detalhes_post")
    private Long codigo;

    @Column(name = "nm_autor", length = 50, nullable = false)
    private String nomeAutor;

    @Column(name = "dt_criacao", nullable = false)
    @CreatedDate
    private LocalDate dataCriacao;

    @Column(name = "dt_publicacao", nullable = false)
    private LocalDate dataPublicacao;

    @OneToOne
    @JoinColumn(name = "cd_post", nullable = false, unique = true)
    private Post post;

    public DetalhesPost(CadastrarPostDTO dto){
        nomeAutor = dto.autor();
        dataPublicacao = dto.dataPublicacao();
    }

}
