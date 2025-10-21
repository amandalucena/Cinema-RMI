
package DAO;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject; // usado para exportar objetos remotos 
import servidor.model.FilmeModel;
import servidor.util.Conection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import servidor.controller.InterfaceFilme;



public class FilmeDAO extends UnicastRemoteObject implements InterfaceFilme{
    public FilmeDAO() throws RemoteException {
        super ();
    }

    @Override
    public boolean inserir(FilmeModel filme) throws RemoteException {
        boolean retorno = false;
        Conection c = new Conection();
        c.conectar();
        String sql = "insert into filme(titulo,genero, duracao_minutos, classificacao_indicativa) values (?,?,?,?)";
        try{
            PreparedStatement sentenca = c.conector.prepareStatement(sql);
            sentenca.setString(1, filme.getTitulo());
            sentenca.setString(2, filme.getGenero());
            sentenca.setInt(3, filme.getDuracao());
            sentenca.setString(4, filme.getClassificacao_indicativa());
            if(!sentenca.execute())
                retorno = true;
        }catch(SQLException e){
            System.out.println("Erro ao inserir: "+ e.getMessage());
        }
        c.desconectar();
        return retorno;
        
    }

    @Override
    public boolean editar(FilmeModel filme) throws RemoteException {
        boolean retorno = false;
        Conection c = new Conection ();
        c.conectar();
        String sql = "update filme set titulo = ?, gener0 = ?, duracao_minutos = ?, classificaca0_indicativa = ? ";
        try{
            PreparedStatement sentenca = c.conector.prepareStatement(sql);
            sentenca.setString(1, filme.getTitulo());
            sentenca.setString(2, filme.getGenero());
            sentenca.setInt(3, filme.getDuracao());
            sentenca.setString(4, filme.getClassificacao_indicativa());
            if(!sentenca.execute())
                retorno = true;
        }catch(SQLException e){
            System.out.println("Erro ao editar: "+ e.getMessage());
        }
        c.desconectar ();
        return retorno;
    }

    @Override
    public boolean excluir(FilmeModel filme) throws RemoteException {
        boolean retorno = false;
        Conection c = new Conection ();
        String sql = "delete from filme where idFilme = ? ";
        try{
            PreparedStatement sentenca = c.conector.prepareStatement(sql);
            sentenca.setInt(1, filme.getIdFilme());
            if(!sentenca.execute())
                retorno = true;
        }catch(SQLException e){
            System.out.println("Erro ao editar: "+ e.getMessage());
        }
        c.desconectar ();
        return retorno;
        
        
    }

    @Override
    public FilmeModel pesquisar(FilmeModel filme) throws RemoteException {
        FilmeModel retorno = null;
        Conection c = new Conection();
        c.conectar();
        String sql = "select * from filme where id= ?";
        try{
            PreparedStatement sentenca = c.conector.prepareStatement(sql);
            sentenca.setInt(1, filme.getIdFilme());
            ResultSet rs = sentenca.executeQuery();
            if(rs.next()){
                retorno = new FilmeModel();
                retorno.setIdFilme(rs.getInt("id"));
                retorno.setTitulo(rs.getString("titulo"));
                retorno.setGenero(rs.getString("genero"));
                retorno.setDuracao (rs.getInt ("duracao"));
                retorno.setClassificacao_indicativa(rs.getString ("classificacao_indicativa"));
            }
        }catch(SQLException e){
            System.out.println("Erro ao pesquisar: "+ e.getMessage());
        }
        c.desconectar();
        return retorno;
    }
    
}
