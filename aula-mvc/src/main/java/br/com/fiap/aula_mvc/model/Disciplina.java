package br.com.fiap.aula_mvc.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Disciplina {

    private Double cp;
    private Double challenge;
    private Double gs;
    private Double media;

    public Double calcularMediaSemestre(){
        return cp * 0.2 + challenge * 0.2 + gs * 0.6;
    }

    public Double calculadrMediaAnual(){
        return calcularMediaSemestre() * 0.6 + media * 0.4;
    }
}
