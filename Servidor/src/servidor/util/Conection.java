
package servidor.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conection {
    public Connection conector;
    public void conectar(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:/cinema_db";
            String usuario = "root";
            String senha = "1234";
            conector = DriverManager.getConnection(url, usuario, senha);
        }catch(ClassNotFoundException e){
            System.out.println("Erro no Driver: "+ e.getMessage());
        }catch(SQLException e){
            System.out.println("Erro na conexão com banco!"+ e.getMessage());
        }
    }
    public void desconectar(){
        try{
            conector.close();
        }catch(SQLException e){
            System.out.println("Erro na conexão com banco!"+ e.getMessage());
        }
    }
}
