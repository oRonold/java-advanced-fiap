package br.com.fiap.relacoes.service;

import br.com.fiap.relacoes.model.user.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;

@Service
public class TokenService {

    @Value("${api.token.secret}")
    private String senhaToken;

    public String gerarToken(Usuario usuario){
        try {
            Algorithm algoritmo = Algorithm.HMAC256(senhaToken);
            return JWT.create()
                    .withIssuer("API FIAP")
                    .withSubject(usuario.getLogin())
                    .withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
                    .sign(algoritmo);
        } catch (JWTCreationException e){
            throw new RuntimeException("Erro ao gerar token jwt");
        }
    }
}
