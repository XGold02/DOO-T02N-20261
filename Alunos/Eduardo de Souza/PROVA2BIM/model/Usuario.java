package provaDOO.model;

import java.util.ArrayList;
import java.util.List;

public class Usuario {

    private Long id;
    private String nome;

    private List<Long> favoritos;
    private List<Long> assistidas;
    private List<Long> desejaAssistir;

    public Usuario() {
        favoritos = new ArrayList<>();
        assistidas = new ArrayList<>();
        desejaAssistir = new ArrayList<>();
    }

    public Long getId() {return id;}
    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {return nome;}
    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Long> getFavoritos() {return favoritos;}
    public void setFavoritos(List<Long> favoritos) {
        this.favoritos = favoritos;
    }

    public List<Long> getAssistidas() {return assistidas;}
    public void setAssistidas(List<Long> assistidas) {
        this.assistidas = assistidas;
    }

    public List<Long> getDesejaAssistir() {return desejaAssistir;}
    public void setDesejaAssistir(List<Long> desejaAssistir) {
        this.desejaAssistir = desejaAssistir;
    }
}