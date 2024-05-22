package br.com.fiap.tour.repository;

import br.com.fiap.tour.domain.Cliente;
import br.com.fiap.tour.domain.Pacote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    //Criar a pesquisa de cliente por data de nascimento
    @Query("select c from Cliente c where c.dataNascimento = ?1")
    Page<Cliente> buscarPorDataNascimento(LocalDate dataNascimento, Pageable page);

    @Query("from Cliente c where lower(c.nome) like lower(concat('%',?1,'%'))")
    Page<Cliente> buscarPorNome(String nome, Pageable page);

    @Query("from Cliente c where c.endereco.cidade.nome like %?1%")
    Page<Cliente> buscarPorEstado(String nome, Pageable pageable);

    @Query("select r.cliente from Reserva r where r.pacote.valor > ?1")
    Page<Cliente> buscarPorValorPacote(Double valor, Pageable pageable);

    @Query("from Cliente c where c.nome like %?1% and c.endereco.cidade.nome like %?2%")
    Page<Cliente> buscarPorNomeClienteCidade(String cliente, String cidade, Pageable pageable);

    @Query("from Cliente c where c.endereco.cidade.uf in ?1")
    Page<Cliente> listarPorEstado(List<String> estados, Pageable pageable);

    @Query("select count(c) from Cliente c where c.endereco.cidade.uf = ?1")
    Long contarQtdClienteEstado(String estado);

}
