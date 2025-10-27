package client;

// Importações necessárias
import contract.CinemaRMI;
import view.Main;
import java.rmi.Naming;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Client {

    // CORREÇÃO: IP FIXO DO SERVIDOR
    private static final String SERVER_IP = "192.168.18.230";
    
    public static void main(String[] args) {
        
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("Não foi possível aplicar o Look and Feel do sistema.");
        }

        try {
            // URL corrigida para apontar para o IP real do servidor
            String serverURL = "rmi://" + SERVER_IP + "/CinemaService";

            System.out.println("Conectando ao servidor em: " + serverURL);
            
            // O cliente procura o serviço
            CinemaRMI cinemaService = (CinemaRMI) Naming.lookup(serverURL);
            
            System.out.println("Conexão bem-sucedida!");

            // Cria e exibe a tela principal
            Main telaPrincipal = new Main(cinemaService);
            telaPrincipal.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                null,
                "Não foi possível conectar ao servidor (" + SERVER_IP + ").\n" +
                "Verifique o firewall (portas 1099 e 2024) e o status do serviço.",
                "Erro de Conexão RMI",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
}