package br.com.fiap.relacoes.repository;

import br.com.fiap.relacoes.model.blog.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
