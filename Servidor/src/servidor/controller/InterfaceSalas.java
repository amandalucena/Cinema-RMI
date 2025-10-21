
package servidor.controller;

import java.rmi.RemoteException;
import servidor.model.SalasModel;


public interface InterfaceSalas {
    public boolean inserir(SalasModel sala)throws RemoteException;
    public boolean editar(SalasModel sala) throws RemoteException;
    public boolean excluir (SalasModel sala)throws RemoteException;
    public SalasModel pesquisar(SalasModel sala) throws RemoteException;
    
}
