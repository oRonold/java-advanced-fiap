package br.com.fiap.relacoes.repository;

import br.com.fiap.relacoes.model.user.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
