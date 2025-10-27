
package servidor.controller;

import contract.model.SessaoModel;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfaceSessoes extends Remote {

    public boolean inserir(SessaoModel sessao) throws RemoteException;

    public boolean editar(SessaoModel sessao) throws RemoteException;

    public boolean excluir(SessaoModel sessao) throws RemoteException;

    public SessaoModel pesquisar(SessaoModel sessao) throws RemoteException;

}
