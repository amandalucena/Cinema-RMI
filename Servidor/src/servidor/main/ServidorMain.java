package servidor.main;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.util.logging.Level;
import java.util.logging.Logger;
import servidor.rmi.CinemaRMIImpl;

public class ServidorMain {

    private static final Logger LOGGER = Logger.getLogger(ServidorMain.class.getName());

    public static void main(String[] args) {
        try {
            // 1. Inicia o RMI Registry na porta padrão 1099 [cite: 131]
            LocateRegistry.createRegistry(1099);
            LOGGER.info("RMI Registry iniciado na porta 1099.");

            // 2. Instancia sua implementação dos métodos RMI [cite: 132]
            CinemaRMIImpl cinemaImpl = new CinemaRMIImpl();
            LOGGER.info("Implementação do servidor instanciada.");

            // 3. Registra o serviço no RMI Registry [cite: 134, 135]
            // O cliente usará este nome exato para encontrar o serviço.
            Naming.rebind("rmi://localhost/CinemaService", cinemaImpl);

            LOGGER.info("*****************************");
            LOGGER.info("Servidor RMI no ar!");
            LOGGER.info("Aguardando conexões de clientes...");
            LOGGER.info("*****************************");

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro ao iniciar o servidor RMI:", e);
        }
    }
}