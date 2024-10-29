package br.com.fiap.aula_mvc.model.role;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
}
