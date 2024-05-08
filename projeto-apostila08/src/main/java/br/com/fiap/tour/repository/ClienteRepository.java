package br.com.fiap.tour.repository;

import br.com.fiap.tour.domain.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query("from Cliente c where c.dataNascimento = ?1")
    Page<Cliente> buscarPorDataNascimento(LocalDate dataNascimento, Pageable pageable);

    @Query("from Cliente c where c.nome like %?1%")
    Page<Cliente> buscarPorParteDoNome(String nome, Pageable pageable);


}
