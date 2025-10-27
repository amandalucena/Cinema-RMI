package contract.model;

import java.io.Serializable;

public class FilmeModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String titulo;
    private String genero;
    private int duracaoMinutos;
    private String classificacaoIndicativa;

    public FilmeModel() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    // Backwards-compatible aliases (used by server code originally)
    public int getIdFilme() { return getId(); }
    public void setIdFilme(int id) { setId(id); }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }

    public int getDuracaoMinutos() { return duracaoMinutos; }
    public void setDuracaoMinutos(int duracaoMinutos) { this.duracaoMinutos = duracaoMinutos; }

    // Alias
    public int getDuracao() { return getDuracaoMinutos(); }
    public void setDuracao(int duracao) { setDuracaoMinutos(duracao); }

    public String getClassificacaoIndicativa() { return classificacaoIndicativa; }
    public void setClassificacaoIndicativa(String classificacaoIndicativa) { this.classificacaoIndicativa = classificacaoIndicativa; }
}
