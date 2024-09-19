package br.com.fiap.aula_mvc.repository;

import br.com.fiap.aula_mvc.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long> {
}
