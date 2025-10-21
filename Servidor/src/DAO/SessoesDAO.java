
package DAO;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject; 
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import servidor.controller.InterfaceSessoes;
import servidor.model.SessoesModel;
import servidor.util.Conection;

public class SessoesDAO extends UnicastRemoteObject implements InterfaceSessoes{

    public SessoesDAO() throws RemoteException {
    }

    @Override
    public boolean inserir(SessoesModel sessao) throws RemoteException {
        boolean retorno = false;
        Conection c = new Conection();
        c.conectar();
        String sql = "insert into sessoes (filme_id, sala_id, data_hora, preco_ingresso) values (?,?,?,?)";
        try{
            PreparedStatement sentenca = c.conector.prepareStatement(sql);
            sentenca.setInt(1, sessao.getIdFilme());
            sentenca.setInt(2, sessao.getIdSala());
            sentenca.setTime(3, sessao.getDataHora());
            sentenca.setFloat(4, sessao.getPrecoIngresso());
            
            if(!sentenca.execute())
                retorno = true;
        }catch(SQLException e){
            System.out.println("Erro ao inserir: "+ e.getMessage());
        }
        c.desconectar();
        return retorno;
    }

    @Override
    public boolean editar(SessoesModel sessao) throws RemoteException {
        boolean retorno = false;
        Conection c = new Conection ();
        c.conectar();
        String sql = "update sessoes set filme_id = ?, sala_id = ?, data_hora= ?, preco_ingresso =? ";
        try{
            PreparedStatement sentenca = c.conector.prepareStatement(sql);
            sentenca.setInt(1, sessao.getIdFilme());
            sentenca.setInt(2, sessao.getIdSala());
            sentenca.setTime(3, sessao.getDataHora());
            sentenca.setFloat (4, sessao.getPrecoIngresso());
           
            if(!sentenca.execute())
                retorno = true;
        }catch(SQLException e){
            System.out.println("Erro ao editar: "+ e.getMessage());
        }
        c.desconectar ();
        return retorno;
    }

    @Override
    public boolean excluir(SessoesModel sessao) throws RemoteException {
        boolean retorno = false;
        Conection c = new Conection ();
        String sql = "delete from sessoes where id = ? ";
        try{
            PreparedStatement sentenca = c.conector.prepareStatement(sql);
            sentenca.setInt(1, sessao.getIdSessoes());
            if(!sentenca.execute())
                retorno = true;
        }catch(SQLException e){
            System.out.println("Erro ao excluir: "+ e.getMessage());
        }
        c.desconectar ();
        return retorno;
    }

    @Override
    public SessoesModel pesquisar(SessoesModel sessao) throws RemoteException {
       SessoesModel retorno = null;
        Conection c = new Conection();
        c.conectar();
        String sql = "select * from sessoes where id = ?";
        try{
            PreparedStatement sentenca = c.conector.prepareStatement(sql);
            sentenca.setInt(1, sessao.getIdSessoes());
            ResultSet rs = sentenca.executeQuery();
            if(rs.next()){
                retorno = new SessoesModel();
                retorno.setIdSessoes(rs.getInt("id"));
                retorno.setIdFilme (rs.getInt("filme_id"));
                retorno.setIdSala(rs.getInt("sala_id"));
                retorno.setDataHora(rs.getTime("data_hora"));
                retorno.setPrecoIngresso(rs.getFloat("preco_ingresso"));
            }
        }catch(SQLException e){
            System.out.println("Erro ao pesquisar: "+ e.getMessage());
        }
        c.desconectar();
        return retorno;
    }
    
    
}
