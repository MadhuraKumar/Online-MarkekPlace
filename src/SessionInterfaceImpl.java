import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;

/**
 * This class provides the implementation of sessionInterface class
 * 
 * @author Madhura
 *
 */

public class SessionInterfaceImpl extends UnicastRemoteObject implements SessionInterface {
   
	private static final long serialVersionUID = 1L;
	private String sessionID;
	private String role;
	
	
	public SessionInterfaceImpl(String sessionID, String role) throws RemoteException{
		super();
		Random rn = new Random();
		this.sessionID = sessionID + String.valueOf(rn.nextInt(50));
		this.role = role;
	}
	
	@Override
	public synchronized String getSessionID(){
		
		return sessionID;
	}
	
	@Override
	public synchronized String getRole(){
		return role;
	}
	
}
