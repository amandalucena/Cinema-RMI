package contract.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class SessaoModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private int filmeId;
    private int salaId;
    private LocalDateTime dataHora;
    private double precoIngresso;
    private int numeroSala;

    public SessaoModel() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getFilmeId() { return filmeId; }
    public void setFilmeId(int filmeId) { this.filmeId = filmeId; }

    public int getSalaId() { return salaId; }
    public void setSalaId(int salaId) { this.salaId = salaId; }

    public LocalDateTime getDataHora() { return dataHora; }
    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }

    public double getPrecoIngresso() { return precoIngresso; }
    public void setPrecoIngresso(double precoIngresso) { this.precoIngresso = precoIngresso; }

    public int getNumeroSala() { return numeroSala; }
    public void setNumeroSala(int numeroSala) { this.numeroSala = numeroSala; }
}
