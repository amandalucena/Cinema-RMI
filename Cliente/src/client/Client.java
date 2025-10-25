package client;

// Importações necessárias
import contract.CinemaRMI;
import view.Main;
import java.rmi.Naming;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Client {

    public static void main(String[] args) {
        // Tenta aplicar um Look and Feel mais moderno à interface (Opcional)
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("Não foi possível aplicar o Look and Feel do sistema.");
        }

        try {
            // Endereço onde o serviço RMI está rodando.
            // Mude "localhost" para o IP da máquina do servidor.
            String serverURL = "rmi://localhost/CinemaService";

            System.out.println("Conectando ao servidor em: " + serverURL);
            CinemaRMI cinemaService = (CinemaRMI) Naming.lookup(serverURL);
            System.out.println("Conexão bem-sucedida!");

            // Cria e exibe a tela principal, passando a conexão RMI para ela.
            Main telaPrincipal = new Main(cinemaService);
            telaPrincipal.setVisible(true);

        } catch (Exception e) {
            // Se qualquer erro ocorrer durante a conexão, exibe uma mensagem.
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                null,
                "Não foi possível conectar ao servidor.\n" +
                "Verifique se o servidor está em execução e o endereço está correto.",
                "Erro de Conexão RMI",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
}