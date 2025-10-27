package test;

import contract.CinemaRMI;
import view.Main;

/**
 * Esta classe é o ponto de entrada (Main) para testar sua aplicação offline.
 * Ela vai criar o servidor "falso" e injetá-lo na sua tela Main.
 */
public class TestadorOffline {

    public static void main(String[] args) {
        // 1. Cria uma instância do nosso serviço RMI falso
        CinemaRMI servicoFalso = new MockCinemaService();
        
        // 2. Cria sua tela Main, exatamente como o Client.java faria,
        //    mas passando o serviço falso.
        Main telaPrincipal = new Main(servicoFalso);
        
        // 3. Torna a tela visível
        telaPrincipal.setVisible(true);
    }
}