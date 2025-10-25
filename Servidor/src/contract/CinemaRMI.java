/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package contract;

import contract.model.FilmeModel;
import contract.model.SessaoModel;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * CinemaRMI (Remote Method Invocation Interface)
 * Esta interface é o contrato que define todos os métodos que o cliente
 * pode chamar remotamente no servidor.
 * Ela precisa obrigatoriamente estender a interface 'java.rmi.Remote'.
 */
public interface CinemaRMI extends Remote {

    /**
     * Retorna uma lista de todos os filmes atualmente em cartaz.
    * @return uma Lista de objetos FilmeModel.
     * @throws RemoteException se ocorrer um erro de comunicação RMI.
     */
    List<FilmeModel> listarFilmes() throws RemoteException;

    /**
     * Retorna todas as sessões disponíveis para um filme específico.
     * @param filmeId O ID do filme para o qual se deseja buscar as sessões.
    * @return uma Lista de objetos SessaoModel.
     * @throws RemoteException se ocorrer um erro de comunicação RMI.
     */
    List<SessaoModel> listarSessoesPorFilme(int filmeId) throws RemoteException;

    /**
     * Retorna uma lista com os nomes das poltronas que já foram vendidas
     * para uma sessão específica.
     * @param sessaoId O ID da sessão a ser verificada.
     * @return uma Lista de Strings, onde cada String é um assento (ex: "A1", "C12").
     * @throws RemoteException se ocorrer um erro de comunicação RMI.
     */
    List<String> getPoltronasOcupadas(int sessaoId) throws RemoteException;

    /**
     * Tenta realizar a compra de um ingresso para uma determinada sessão.
     * @param sessaoId O ID da sessão para a qual o ingresso será comprado.
     * @param nomeCliente O nome do cliente que está comprando o ingresso.
     * @param poltrona A poltrona escolhida pelo cliente (ex: "B5").
     * @return uma String contendo uma mensagem de sucesso ou de falha (ex: "Poltrona já ocupada").
     * @throws RemoteException se ocorrer um erro de comunicação RMI.
     */
    String comprarIngresso(int sessaoId, String nomeCliente, String poltrona) throws RemoteException;
}