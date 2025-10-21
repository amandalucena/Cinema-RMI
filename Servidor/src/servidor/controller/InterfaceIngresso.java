
package servidor.controller;

import servidor.model.IngressoModel;
import java.rmi.RemoteException; 


public interface InterfaceIngresso {
    public boolean inserir(IngressoModel ingresso)throws RemoteException;
    public boolean editar(IngressoModel ingresso) throws RemoteException;
    public boolean excluir (IngressoModel ingresso)throws RemoteException;
    public IngressoModel pesquisar(IngressoModel ingresso) throws RemoteException;
    
}
