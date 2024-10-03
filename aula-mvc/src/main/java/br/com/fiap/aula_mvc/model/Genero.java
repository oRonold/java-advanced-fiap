package br.com.fiap.aula_mvc.model;

public enum Genero {

    // Enumerated type for book genres like ACAO("Acao")
    ACAO("Ação"),
    AVENTURA("Aventura"),
    COMEDIA("Comédia"),
    DRAMA("Drama"),
    FANTASIA("Fantasia"),
    FICCAO("Ficção"),
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
