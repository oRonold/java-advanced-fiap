package br.com.fiap.relacoes.repository;

import br.com.fiap.relacoes.model.blog.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
}
