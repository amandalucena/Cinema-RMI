
package DAO;

import java.rmi.RemoteException;
import servidor.controller.InterfaceIngresso;
import java.rmi.server.UnicastRemoteObject; // permite que métodos sejam chamados remotamente, ou seja, entre diferentes computadores pela rede.
import servidor.model.IngressoModel;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import servidor.util.Conection;

public class IngressoDAO extends UnicastRemoteObject implements InterfaceIngresso{

    
    // o contrutor serve para registrar o objeto para comunicação remota do RMI
    public IngressoDAO() throws RemoteException {
        super ();
    }
    

    @Override
    public boolean inserir(IngressoModel ingresso) throws RemoteException {
        boolean retorno = false;
        Conection c = new Conection();
        c.conectar();
        String sql = "insert into ingressos (sessao_id, nome_cliente, poltrona) values (?,?,?)";
        try{
            PreparedStatement sentenca = c.conector.prepareStatement(sql);
            sentenca.setInt(1, ingresso.getSessao_id());
            sentenca.setString(2, ingresso.getNome_cliente());
            sentenca.setString(3, ingresso.getPoltrona());
            if(!sentenca.execute())
                retorno = true;
        }catch(SQLException e){
            System.out.println("Erro ao inserir: "+ e.getMessage());
        }
        c.desconectar();
        return retorno;
    }

    @Override
    public boolean editar(IngressoModel ingresso) throws RemoteException {
        boolean retorno = false;
        Conection c = new Conection ();
        c.conectar();
        String sql = "update ingressos set sessao_id = ?, nome_cliente = ?, poltrona = ? ";
        try{
            PreparedStatement sentenca = c.conector.prepareStatement(sql);
            sentenca.setInt(1, ingresso.getSessao_id());
            sentenca.setString(2, ingresso.getNome_cliente());
            sentenca.setString(3, ingresso.getPoltrona());
            if(!sentenca.execute())
                retorno = true;
        }catch(SQLException e){
            System.out.println("Erro ao editar: "+ e.getMessage());
        }
        c.desconectar ();
        return retorno;
    }
        

    @Override
    public boolean excluir(IngressoModel ingresso) throws RemoteException {
        boolean retorno = false;
        Conection c = new Conection ();
        String sql = "delete from ingressos where idIngresso = ? ";
        try{
            PreparedStatement sentenca = c.conector.prepareStatement(sql);
            sentenca.setInt(1, ingresso.getIdIngresso());
            if(!sentenca.execute())
                retorno = true;
        }catch(SQLException e){
            System.out.println("Erro ao editar: "+ e.getMessage());
        }
        c.desconectar ();
        return retorno;
}

        @Override
        public IngressoModel pesquisar(IngressoModel ingresso) throws RemoteException {
        IngressoModel retorno = null;
        Conection c = new Conection();
        c.conectar();
        String sql = "select * from ingressos where id = ?";
        try{
            PreparedStatement sentenca = c.conector.prepareStatement(sql);
            sentenca.setInt(1, ingresso.getIdIngresso());
            ResultSet rs = sentenca.executeQuery();
            if(rs.next()){
                retorno = new IngressoModel();
                retorno.setIdIngresso(rs.getInt("id"));
                retorno.setSessao_id(rs.getInt("sessao_id"));
                retorno.setNome_cliente(rs.getString("nome_cliente"));
                retorno.setPoltrona(rs.getString("poltrona"));
                
            }
        }catch(SQLException e){
            System.out.println("Erro ao pesquisar: "+ e.getMessage());
        }
        c.desconectar();
        return retorno;
            
        }
       
    }

    

