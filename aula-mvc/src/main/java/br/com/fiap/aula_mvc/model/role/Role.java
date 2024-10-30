package br.com.fiap.aula_mvc.model.role;

import br.com.fiap.aula_mvc.model.user.Usuario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity @Table(name = "role")
@Getter @Setter
public class Role {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "nm_role", nullable = false, unique = true)
    private String name;

    @Column(name = "ds_label", nullable = false)
    private String label;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
    private Set<Usuario> usuarios;
}
