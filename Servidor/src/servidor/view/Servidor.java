
package servidor.view;

import java.rmi.RemoteException;
import DAO.SalasDAO;
import DAO.SessoesDAO;
import java.time.LocalTime;
import servidor.model.SalasModel;
import servidor.model.SessoesModel;
import java.sql.Time;
public class Servidor {

    
    public static void main(String[] args) throws RemoteException {
        try {
            // Cria o DAO
            SessoesDAO dao = new SessoesDAO();

            // ====== TESTE INSERT ======
            SessoesModel novaSessao = new SessoesModel ();
            novaSessao.setIdSala(1);
            novaSessao.setIdFilme(1);
            novaSessao.setDataHora(Time.valueOf("14:30:00"));
            novaSessao.setPrecoIngresso(15);
            
            boolean inseriu = dao.inserir(novaSessao);
            System.out.println("Inseriu sala? " + inseriu);

            // ====== TESTE PESQUISAR ======
            SessoesModel buscar = new SessoesModel();
            buscar.setIdSala(1);
            SessoesModel encontrado = dao.pesquisar(buscar);
            if (encontrado != null) {
                System.out.println("Encontrado: " + encontrado.getDataHora()+ " Preço: " + encontrado.getPrecoIngresso());
            } else {
                System.out.println("Nenhuma sessão encontrada.");
            }

            // TESTE EDITAR 
            if (encontrado != null) {
                encontrado.setPrecoIngresso (30); // Alterando valor
                boolean editou = dao.editar(encontrado);
                System.out.println("Editou sessão? " + editou);
            }

            // ====== TESTE EXCLUIR ======
            if (encontrado != null) {
                boolean excluiu = dao.excluir(encontrado);
                System.out.println("Excluiu sala? " + excluiu);
            }

        } catch (Exception e) {
            System.out.println("Erro no teste: " + e.getMessage());
        }
    }
        
        
        
        
        
    
    }
    
}
