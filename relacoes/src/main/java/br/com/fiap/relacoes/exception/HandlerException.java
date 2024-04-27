package br.com.fiap.relacoes.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandlerException {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Void> erro404(){
        return ResponseEntity.notFound().build();
    }

}
