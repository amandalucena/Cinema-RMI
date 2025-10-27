package test;

import contract.CinemaRMI;
import contract.model.FilmeModel;
import contract.model.SessaoModel;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Esta é uma classe "Mock" (Falsa) que implementa a interface RMI.
 * Ela não se conecta a nenhum servidor. Ela apenas retorna dados
 * de exemplo para que você possa testar sua interface gráfica offline.
 * * VERSÃO 2: Agora simula SESSÕES e POLTRONAS.
 */
public class MockCinemaService implements CinemaRMI {

    // --- DADOS FALSOS PARA AS POLTRONAS ---
    // Vamos guardar as poltronas compradas aqui para simular uma compra real.
    // (O "List.of" cria uma lista, e o "new ArrayList<>" torna ela modificável)
    private List<String> poltronasSessao1001 = new ArrayList<>(List.of("A1", "C2"));
    private List<String> poltronasSessao1002 = new ArrayList<>(List.of("B1", "D3"));
    private List<String> poltronasSessao1003 = new ArrayList<>(List.of("A2", "B2", "C2"));


    @Override
    public List<FilmeModel> listarFilmes() throws RemoteException {
        // Simula um pequeno atraso da rede (1 segundo)
        simularRede(1000); 
        
    List<FilmeModel> filmesFalsos = new ArrayList<>();
        
            FilmeModel f1 = new FilmeModel();
        f1.setId(901); // ID Falso 1
        f1.setTitulo("Duna: Parte 2 (TESTE OFFLINE)");
        f1.setGenero("Ficção Científica");
        f1.setClassificacaoIndicativa("14 anos");
        filmesFalsos.add(f1);

            FilmeModel f2 = new FilmeModel();
        f2.setId(902); // ID Falso 2
        f2.setTitulo("Interestelar (TESTE OFFLINE)");
        f2.setGenero("Ficção Científica");
        f2.setClassificacaoIndicativa("12 anos");
        filmesFalsos.add(f2);
        
        return filmesFalsos;
    }
    
    @Override
    public List<SessaoModel> listarSessoesPorFilme(int filmeId) throws RemoteException {
        // Simula um atraso da rede (1 segundo)
        simularRede(1000);
        
    List<SessaoModel> sessoesFalsas = new ArrayList<>();
        
        // Se o usuário clicou em Duna (ID 901)
        if (filmeId == 901) {
              SessaoModel s1 = new SessaoModel();
            s1.setId(1001); // ID Falso da Sessão
            s1.setFilmeId(filmeId);
            s1.setNumeroSala(1); // Número da Sala
            s1.setDataHora(LocalDateTime.of(2025, 10, 20, 18, 00)); // Data/Hora
            s1.setPrecoIngresso(30.00);
            sessoesFalsas.add(s1);
            
              SessaoModel s2 = new SessaoModel();
            s2.setId(1002); // ID Falso da Sessão
            s2.setFilmeId(filmeId);
            s2.setNumeroSala(5); // Número da Sala
            s2.setDataHora(LocalDateTime.of(2025, 10, 20, 21, 00)); // Data/Hora
            s2.setPrecoIngresso(35.50);
            sessoesFalsas.add(s2);
        }
        
        // Se o usuário clicou em Interestelar (ID 902)
        if (filmeId == 902) {
              SessaoModel s3 = new SessaoModel();
            s3.setId(1003); // ID Falso da Sessão
            s3.setFilmeId(filmeId);
            s3.setNumeroSala(2); // Número da Sala
            s3.setDataHora(LocalDateTime.of(2025, 10, 21, 20, 30)); // Data/Hora
            s3.setPrecoIngresso(40.00);
            sessoesFalsas.add(s3);
        }

        return sessoesFalsas; 
    }

    @Override
    public List<String> getPoltronasOcupadas(int sessaoId) throws RemoteException {
        // Simula um atraso da rede (meio segundo)
        simularRede(500);
        
        // Retorna a lista de poltronas ocupadas correspondente à sessão
        if (sessaoId == 1001) {
            return poltronasSessao1001;
        }
        if (sessaoId == 1002) {
            return poltronasSessao1002;
        }
         if (sessaoId == 1003) {
            return poltronasSessao1003;
        }
        
        // Se for uma sessão desconhecida, retorna uma lista vazia
        return new ArrayList<>();
    }

    @Override
    public String comprarIngresso(int sessaoId, String nomeCliente, String poltrona) throws RemoteException {
        // Simula o processamento da compra (1.5 segundos)
        simularRede(1500);
        
        // Lógica de compra Falsa
        List<String> poltronasDaSessao = getPoltronasOcupadas(sessaoId); // Pega a lista correta
        
        if (poltronasDaSessao.contains(poltrona)) {
            // Se a poltrona já estava na lista, falha
            return "Falha! A poltrona " + poltrona + " já está ocupada.";
        } else {
            // Se não estava, adiciona na lista e retorna sucesso
            poltronasDaSessao.add(poltrona);
            return "Ingresso para a poltrona " + poltrona + " comprado com sucesso por " + nomeCliente + "!";
        }
    }
    
    // Método auxiliar para simular a demora da rede
    private void simularRede(int milissegundos) {
        try {
            Thread.sleep(milissegundos);
        } catch (InterruptedException e) {
            // não faz nada
        }
    }
}