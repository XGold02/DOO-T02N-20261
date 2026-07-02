package provaDOO.model;

import java.util.List;

public class Serie {

    private Long id;
    private String nome;
    private String idioma;
    private List<String> generos;
    private Double nota;
    private String status;
    private String estreia;
    private String termino;
    private String emissora;

    public Long getId() {return id;}
    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {return nome;}
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIdioma() {return idioma;}
    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public List<String> getGeneros() {return generos;}
    public void setGeneros(List<String> generos) {
        this.generos = generos;
    }

    public Double getNota() {return nota;}
    public void setNota(Double nota) {
        this.nota = nota;
    }

    public String getStatus() {return status;}
    public void setStatus(String status) {
        this.status = status;
    }

    public String getEstreia() {return estreia;}
    public void setEstreia(String estreia) {
        this.estreia = estreia;
    }

    public String getTermino() {return termino;}
    public void setTermino(String termino) {
        this.termino = termino;
    }

    public String getEmissora() {return emissora;}
    public void setEmissora(String emissora) {
        this.emissora = emissora;
    }
}