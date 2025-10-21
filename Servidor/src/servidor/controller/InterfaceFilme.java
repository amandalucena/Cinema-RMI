
package servidor.controller;
import java.rmi.Remote;
import java.rmi.RemoteException; // indica que o método pode falhar por motivos de rede
import servidor.model.FilmeModel;


public interface InterfaceFilme extends Remote{
    public boolean inserir(FilmeModel filme)throws RemoteException;
    public boolean editar(FilmeModel filme) throws RemoteException;
    public boolean excluir (FilmeModel filme)throws RemoteException;
    public FilmeModel pesquisar(FilmeModel filme) throws RemoteException;
}
