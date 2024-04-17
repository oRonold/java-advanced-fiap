package br.com.fiap.relacoes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class RelacoesApplication {

	public static void main(String[] args) {
		SpringApplication.run(RelacoesApplication.class, args);
	}

}
