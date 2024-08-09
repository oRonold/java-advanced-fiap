package br.com.fiap.relacoes.repository;

import br.com.fiap.relacoes.model.blog.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
