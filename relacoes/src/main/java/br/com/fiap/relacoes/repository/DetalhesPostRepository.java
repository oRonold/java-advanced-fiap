package br.com.fiap.relacoes.repository;

import br.com.fiap.relacoes.model.blog.DetalhesPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetalhesPostRepository extends JpaRepository<DetalhesPost, Long> {
}
