package br.com.fiap.relacoes.model;

import br.com.fiap.relacoes.dto.AtualizarTagDTO;
import br.com.fiap.relacoes.dto.CadastrarTagDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "EXE_JV_TB_TAG")
@EntityListeners(AuditingEntityListener.class)
public class Tag {

    @Id
    @GeneratedValue
    @Column(name = "cd_tag")
    private Long codigo;

    @Column(name = "nm_tag", length = 20, nullable = false)
    private String nome;

    @ManyToMany(mappedBy = "tag", cascade = CascadeType.ALL)
    private List<Post> post;

    public Tag(CadastrarTagDTO dto){
        this.nome = dto.nome();
    }

    public void atualizar(AtualizarTagDTO dto) {
        if(dto.nome() != null){
            this.nome = dto.nome();
        }
    }
}
