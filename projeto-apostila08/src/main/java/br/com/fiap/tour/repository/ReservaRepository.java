package br.com.fiap.tour.repository;

import br.com.fiap.tour.domain.Reserva;
import org.springframework.cglib.core.Local;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    Page<Reserva> findByDataBetween(LocalDate inicio, LocalDate fim, Pageable pageable);

}
