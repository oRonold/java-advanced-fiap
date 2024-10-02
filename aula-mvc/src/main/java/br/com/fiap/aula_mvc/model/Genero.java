package br.com.fiap.aula_mvc.model;

public enum Genero {

    // Enumerated type for book genres like ACAO("Acao")
    ACAO("Acao"),
    AVENTURA("Aventura"),
    COMEDIA("Comedia"),
    DRAMA("Drama"),
    FANTASIA("Fantasia"),
    FICCAO("Ficcao"),
    ROMANCE("Romance"),
    SUSPENSE("Suspense"),
    TERROR("Terror");

    private String label;

    Genero(String label){
        this.label = label;
    }

    public String getLabel(){
        return label;
    }
}
