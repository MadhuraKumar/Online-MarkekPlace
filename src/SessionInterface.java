import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface class to access session details
 * 
 * @author Madhura
 *
 */

public interface SessionInterface extends Remote {
	
	public String getSessionID() throws RemoteException;
	
	public String getRole() throws RemoteException;
	
}
