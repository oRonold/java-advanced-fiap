package br.com.fiap.relacoes.model;

import br.com.fiap.relacoes.dto.AtualizarComentarioDTO;
import br.com.fiap.relacoes.dto.CadastrarComentarioDTO;
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
@Table(name = "EXE_JV_TB_COMENTARIO")
@EntityListeners(AuditingEntityListener.class)
public class Comentario {

    @Id
    @GeneratedValue
    @Column(name = "cd_comentario")
    private Long codigo;

    @Column(name = "ds_conteudo", length = 100, nullable = false)
    private String conteudo;

    @Column(name = "dt_criacao", nullable = false)
    @CreatedDate
    private LocalDate dataCriacao;

    @Column(name = "nm_autor", length = 50)
    private String nomeAutor;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "cd_post", nullable = false)
    private Post post;

    public Comentario(CadastrarComentarioDTO dto){
        conteudo = dto.conteudo();
        nomeAutor = dto.autor();
    }

    public void atualizar(AtualizarComentarioDTO dto){
        if(dto.autor() != null){
            this.nomeAutor = dto.autor();
        }
        if(dto.conteudo() != null){
            this.conteudo = dto.conteudo();
        }
    }


}
