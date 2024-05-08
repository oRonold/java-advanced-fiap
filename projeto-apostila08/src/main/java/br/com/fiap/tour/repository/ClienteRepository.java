package br.com.fiap.tour.repository;

import br.com.fiap.tour.domain.Cliente;
import br.com.fiap.tour.domain.Pacote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    //Criar a pesquisa de cliente por data de nascimento
    @Query("select c from Cliente c where c.dataNascimento = :data")
    Page<Cliente> buscarPorDataNascimento(@Param("data") LocalDate dataNascimento, Pageable page);

    @Query("from Cliente c where c.nome like %?1%")
    Page<Cliente> buscarPorNome(String nome, Pageable page);

    @Query("from Cliente c where c.endereco.cidade.nome like %?1%")
    Page<Cliente> buscarPorCidade(String nome, Pageable pageable);

    @Query("select object(c) from Cliente c inner join Reserva r on(c.id = r.cliente.id) where r.pacote.valor > ?1")
    Page<Cliente> buscarPorPrecoMaior(Double valor, Pageable pageable);

}
