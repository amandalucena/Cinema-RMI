/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servidor.model;

/**
 *
 * @author User
 */
public class IngressoModel {
    private int idIngresso;
    private int sessao_id;
    private String nome_cliente;
    private String poltrona;

    public int getIdIngresso() {
        return idIngresso;
    }

    public int getSessao_id() {
        return sessao_id;
    }

    public String getNome_cliente() {
        return nome_cliente;
    }

    public String getPoltrona() {
        return poltrona;
    }

    public void setIdIngresso(int id_ingresso) {
        this.idIngresso = id_ingresso;
    }

    public void setSessao_id(int sessao_id) {
        this.sessao_id = sessao_id;
    }

    public void setNome_cliente(String nome_cliente) {
        this.nome_cliente = nome_cliente;
    }

    public void setPoltrona(String poltrona) {
        this.poltrona = poltrona;
    }
    
    
    
    
}
