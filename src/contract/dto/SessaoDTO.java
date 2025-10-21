/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package contract.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *
 * @author wende
 */
public class SessaoDTO implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private int id;
    private int filmeId;
    private int salaId;
    private LocalDateTime dataHora;
    private double precoIngresso;
    
    // Campo adicional que é útil para a view (evita que a view precise saber o ID da sala)
    private int numeroSala;

    // Construtor vazio é uma boa prática para DTOs
    public SessaoDTO() {
    }

    // Getters e Setters para todos os campos

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFilmeId() {
        return filmeId;
    }

    public void setFilmeId(int filmeId) {
        this.filmeId = filmeId;
    }

    public int getSalaId() {
        return salaId;
    }

    public void setSalaId(int salaId) {
        this.salaId = salaId;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public double getPrecoIngresso() {
        return precoIngresso;
    }

    public void setPrecoIngresso(double precoIngresso) {
        this.precoIngresso = precoIngresso;
    }
    
    public int getNumeroSala() {
        return numeroSala;
    }

    public void setNumeroSala(int numeroSala) {
        this.numeroSala = numeroSala;
    }

    // Opcional: um método toString() que ajuda muito na hora de depurar (debugging)
    @Override
    public String toString() {
        return "SessaoDTO{" +
                "id=" + id +
                ", filmeId=" + filmeId +
                ", salaId=" + salaId +
                ", numeroSala=" + numeroSala +
                ", dataHora=" + dataHora +
                ", precoIngresso=" + precoIngresso +
                '}';
    }
    
}
