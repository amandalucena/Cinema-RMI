package contract.model;

import java.io.Serializable;

public class IngressoModel implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private int sessao_id;
    private String nome_cliente;
    private String poltrona;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getSessao_id() { return sessao_id; }
    public void setSessao_id(int sessao_id) { this.sessao_id = sessao_id; }

    public String getNome_cliente() { return nome_cliente; }
    public void setNome_cliente(String nome_cliente) { this.nome_cliente = nome_cliente; }

    public String getPoltrona() { return poltrona; }
    public void setPoltrona(String poltrona) { this.poltrona = poltrona; }
}
