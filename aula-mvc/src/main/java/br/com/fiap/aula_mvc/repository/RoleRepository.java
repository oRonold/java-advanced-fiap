package br.com.fiap.aula_mvc.repository;

import br.com.fiap.aula_mvc.model.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
