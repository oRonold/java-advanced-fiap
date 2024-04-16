package br.com.fiap.store.aula04.repository;

import br.com.fiap.store.aula04.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
