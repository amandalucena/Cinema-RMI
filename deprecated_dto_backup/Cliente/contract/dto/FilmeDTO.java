/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package contract.dto;
import java.io.Serializable;
/**
 *
 * @author wende
 */
public class FilmeDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id; // Corresponde a 'id INT' [cite: 19]
    private String titulo; // Corresponde a 'titulo VARCHAR(255)' [cite: 20]
    private String genero; // Corresponde a 'genero VARCHAR(100)' [cite: 21]
    private int duracaoMinutos; // Corresponde a 'duracao_minutos INT' [cite: 22]
    private String classificacaoIndicativa; // Corresponde a 'classificacao_indicativa VARCHAR(10)' [cite: 23]

    // Construtor vazio
    public FilmeDTO() {
    }

    // Getters e Setters para todos os campos

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getDuracaoMinutos() {
        return duracaoMinutos;
    }

    public void setDuracaoMinutos(int duracaoMinutos) {
        this.duracaoMinutos = duracaoMinutos;
    }

    public String getClassificacaoIndicativa() {
        return classificacaoIndicativa;
    }

    public void setClassificacaoIndicativa(String classificacaoIndicativa) {
        this.classificacaoIndicativa = classificacaoIndicativa;
    }

    // Método toString() para facilitar a depuração (opcional, mas recomendado)
    @Override
    public String toString() {
        return "FilmeDTO{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", genero='" + genero + '\'' +
                ", duracaoMinutos=" + duracaoMinutos +
                ", classificacaoIndicativa='" + classificacaoIndicativa + '\'' +
                '}';
    }
}
