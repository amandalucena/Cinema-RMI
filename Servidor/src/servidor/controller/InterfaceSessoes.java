
package servidor.controller;
import java.rmi.Remote;
import java.rmi.RemoteException; 
import servidor.model.SessoesModel;

public interface InterfaceSessoes extends Remote{
    public boolean inserir(SessoesModel sessao)throws RemoteException;
    public boolean editar(SessoesModel sessao) throws RemoteException;
    public boolean excluir (SessoesModel sessao)throws RemoteException;
    public SessoesModel pesquisar(SessoesModel sessao) throws RemoteException;
    
}
