
package servidor.model;
import java.sql.Time;

public class SessoesModel {
    private int IdSessoes;
    private int IdFilme;
    private int IdSala;
    private Time dataHora;
    private float precoIngresso;

    public int getIdSessoes() {
        return IdSessoes;
    }

    public int getIdFilme() {
        return IdFilme;
    }

    public int getIdSala() {
        return IdSala;
    }

    public Time getDataHora() {
        return dataHora;
    }

    public void setIdSessoes(int IdSessoes) {
        this.IdSessoes = IdSessoes;
    }

    public void setIdFilme(int IdFilme) {
        this.IdFilme = IdFilme;
    }

    public void setIdSala(int IdSala) {
        this.IdSala = IdSala;
    }

    public void setDataHora(Time dataHora) {
        this.dataHora = dataHora;
    }

    public float getPrecoIngresso() {
        return precoIngresso;
    }

    public void setPrecoIngresso(float precoIngresso) {
        this.precoIngresso = precoIngresso;
    }
    
    
    
    
    
}
