package contract.model;

import java.io.Serializable;

public class SalasModel implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private int numeroSala;
    private int capacidade;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getNumeroSala() { return numeroSala; }
    public void setNumeroSala(int numeroSala) { this.numeroSala = numeroSala; }

    public int getCapacidade() { return capacidade; }
    public void setCapacidade(int capacidade) { this.capacidade = capacidade; }
}
