
package servidor.rmi;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import servidor.model.FilmeModel;

public interface Interface extends Remote{
     public String TesteMensagem() throws RemoteException;
     public List<FilmeModel> listarFilmes() throws RemoteException;
}
