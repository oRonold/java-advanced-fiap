package br.com.fiap.aula_mvc.model.user;

import br.com.fiap.aula_mvc.model.role.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity @Table(name = "user")
@Getter @Setter
public class Usuario {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "ds_email", nullable = false, unique = true)
    private String username;

    @Column(name = "ds_senha", nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    public Usuario(String username, String password, Set<Role> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }
}
