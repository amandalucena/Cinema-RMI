package servidor.rmi;

import contract.CinemaRMI;
import contract.model.FilmeModel;
import contract.model.SessaoModel;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import servidor.controller.impl.FilmeController;
import servidor.controller.impl.IngressoController;
import servidor.controller.impl.SessoesController;
import contract.model.IngressoModel;

/**
 * Esta é a implementação real dos métodos RMI.
 * Ela estende UnicastRemoteObject para se tornar um objeto RMI
 * e implementa a interface 'CinemaRMI' que é o contrato.
 */
public class CinemaRMIImpl extends UnicastRemoteObject implements CinemaRMI {

    private static final Logger LOGGER = Logger.getLogger(CinemaRMIImpl.class.getName());

    // Controllers que encapsulam a lógica de acesso/negócio
    private final FilmeController filmeController;
    private final SessoesController sessoesController;
    private final IngressoController ingressoController;

    /**
     * Construtor padrão.
     * Declara 'throws RemoteException' como exigido por UnicastRemoteObject.
     */
    public CinemaRMIImpl() throws RemoteException {
        super(); // Chama o construtor da classe pai (UnicastRemoteObject)
        
    // Instancia os controllers
    this.filmeController = new FilmeController();
    this.sessoesController = new SessoesController();
    this.ingressoController = new IngressoController();
    }

    /**
     * Implementação do método para listar filmes.
     * Busca os dados do banco (Model) e os converte para DTOs.
     */
    @Override
    public List<FilmeModel> listarFilmes() throws RemoteException {
    LOGGER.info("Requisição recebida - listarFilmes()");
        
    List<FilmeModel> listaFilmesDTO = new ArrayList<>();
        
        try {
            // 1. Busca os dados do banco usando o DAO
            // Delegar para o controller
            listaFilmesDTO = filmeController.listarFilmes();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro ao buscar filmes no servidor.", e);
            throw new RemoteException("Erro ao buscar filmes no servidor.", e);
        }
        
        return listaFilmesDTO;
    }

    /**
     * Implementação do método para listar sessões de um filme.
     */
    @Override
    public List<SessaoModel> listarSessoesPorFilme(int filmeId) throws RemoteException {
    LOGGER.info(() -> "Requisição recebida - listarSessoesPorFilme(filmeId=" + filmeId + ")");
        
    List<SessaoModel> listaSessoesDTO = new ArrayList<>();
        
    try {
            // 1. Busca os dados do banco usando o DAO
            // (Este método no DAO deve fazer o JOIN com a tabela 'salas'
            // para pegar o 'numero_sala' e 'preco_ingresso' da 'sessoes')
            // Delegar para o controller (que pode lançar SQLException)
            listaSessoesDTO = sessoesController.listarPorFilme(filmeId);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro ao buscar sessões no servidor.", e);
            throw new RemoteException("Erro ao buscar sessões no servidor.", e);
        }
        
        return listaSessoesDTO;
    }

    /**
     * Implementação do método para buscar poltronas ocupadas.
     */
    @Override
    public List<String> getPoltronasOcupadas(int sessaoId) throws RemoteException {
    LOGGER.info(() -> "Requisição recebida - getPoltronasOcupadas(sessaoId=" + sessaoId + ")");
        
        try {
            // Delegar para o controller
            return ingressoController.getPoltronasOcupadas(sessaoId);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro ao buscar poltronas no servidor.", e);
            throw new RemoteException("Erro ao buscar poltronas no servidor.", e);
        }
    }

    /**
     * Implementação do método para comprar um ingresso.
     */
    @Override
    public String comprarIngresso(int sessaoId, String nomeCliente, String poltrona) throws RemoteException {
    LOGGER.info(() -> "Requisição recebida - comprarIngresso(sessaoId=" + sessaoId + ", poltrona=" + poltrona + ")");
        
        try {
            // 1. Prepara o objeto Model com os dados da tentativa de compra
            IngressoModel novoIngresso = new IngressoModel();
            novoIngresso.setSessao_id(sessaoId);
            novoIngresso.setNome_cliente(nomeCliente);
            novoIngresso.setPoltrona(poltrona);

            // Delegar para o controller
            boolean sucesso = ingressoController.comprarIngresso(novoIngresso);

            if (sucesso) {
                LOGGER.info(() -> "Venda BEM-SUCEDIDA para a poltrona " + poltrona);
                return "Ingresso para a poltrona " + poltrona + " comprado com sucesso!";
            } else {
                // Isso pode acontecer se o DAO pegar a exceção de poltrona duplicada
                LOGGER.info(() -> "Venda FALHOU (poltrona duplicada) para a poltrona " + poltrona);
                return "Falha ao comprar: a poltrona " + poltrona + " já está ocupada.";
            }

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro ao processar compra de ingresso.", e);
            throw new RemoteException("Erro ao processar compra de ingresso.", e);
        }
    }
}