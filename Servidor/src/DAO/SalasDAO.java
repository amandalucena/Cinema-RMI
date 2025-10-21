 
package DAO;

import servidor.controller.InterfaceSalas;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject; //
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import servidor.model.SalasModel;
import servidor.util.Conection;


public class SalasDAO extends UnicastRemoteObject implements InterfaceSalas {

    public SalasDAO() throws RemoteException {
        super();
    }

    @Override
    public boolean inserir(SalasModel sala) throws RemoteException {
        boolean retorno = false;
        Conection c = new Conection();
        c.conectar();
        String sql = "INSERT INTO salas (numero_sala, capacidade) VALUES (?, ?)";
        try {
            PreparedStatement sentenca = c.conector.prepareStatement(sql);
            sentenca.setInt(1, sala.getNumeroSala());
            sentenca.setInt(2, sala.getCapacidade());
            if (sentenca.executeUpdate() > 0) 
                retorno = true;       
        } catch (SQLException e) {
            System.out.println("Erro ao inserir: " + e.getMessage());
        }
        c.desconectar();
        return retorno;
    }

    @Override
    public boolean editar(SalasModel sala) throws RemoteException {
        boolean retorno = false;
        Conection c = new Conection();
        c.conectar();
        String sql = "UPDATE salas SET numero_sala = ?, capacidade = ? WHERE id = ?";
        try {
            PreparedStatement sentenca = c.conector.prepareStatement(sql);
            sentenca.setInt(1, sala.getNumeroSala());
            sentenca.setInt(2, sala.getCapacidade());
            sentenca.setInt(3, sala.getIdSala());
            if (sentenca.executeUpdate() > 0)
                retorno = true;
        } catch (SQLException e) {
            System.out.println("Erro ao editar: " + e.getMessage());
        }
        c.desconectar();
        return retorno;
    }

    @Override
    public boolean excluir(SalasModel sala) throws RemoteException {
        boolean retorno = false;
        Conection c = new Conection();
        c.conectar();
        String sql = "DELETE FROM salas WHERE id = ?";
        try {
            PreparedStatement sentenca = c.conector.prepareStatement(sql);
            sentenca.setInt(1, sala.getIdSala());
            if (sentenca.executeUpdate() > 0)
                retorno = true;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir: " + e.getMessage());
        }
        c.desconectar();
        return retorno;
    }

    @Override
    public SalasModel pesquisar(SalasModel sala) throws RemoteException {
        SalasModel retorno = null;
        Conection c = new Conection();
        c.conectar();
        String sql = "SELECT * FROM salas WHERE id = ?";
        try {
            PreparedStatement sentenca = c.conector.prepareStatement(sql);
            sentenca.setInt(1, sala.getIdSala());
            ResultSet rs = sentenca.executeQuery();
            if (rs.next()) {
                retorno = new SalasModel();
                retorno.setIdSala(rs.getInt("id"));
                retorno.setNumeroSala(rs.getInt("numero_sala"));
                retorno.setCapacidade(rs.getInt("capacidade"));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao pesquisar: " + e.getMessage());
        }
        c.desconectar();
        return retorno;
    }

  
} 
