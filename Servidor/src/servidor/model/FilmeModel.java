
package servidor.model;
import java.io.Serializable;


public class FilmeModel implements Serializable{
    private int idFilme;
    private String titulo;
    private String genero;
    private int duracao;
    private String classificacao_indicativa;

    public int getIdFilme() {
        return idFilme;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getGenero() {
        return genero;
    }

    public int getDuracao() {
        return duracao;
    }

    public String getClassificacao_indicativa() {
        return classificacao_indicativa;
    }

    public void setIdFilme(int idFilme) {
        this.idFilme = idFilme;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public void setClassificacao_indicativa(String classificacao_indicativa) {
        this.classificacao_indicativa = classificacao_indicativa;
    }

    
    
    
    
    
}
