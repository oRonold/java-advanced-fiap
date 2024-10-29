package br.com.fiap.aula_mvc.repository;

import br.com.fiap.aula_mvc.model.user.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByUsername(String email);
}
