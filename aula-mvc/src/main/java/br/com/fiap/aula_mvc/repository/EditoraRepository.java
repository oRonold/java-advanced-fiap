package br.com.fiap.aula_mvc.repository;

import br.com.fiap.aula_mvc.model.Editora;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EditoraRepository extends JpaRepository<Editora, Long> {
}
