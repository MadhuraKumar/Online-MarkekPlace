import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * This is the interface shared between client and server.
 * this class serves as a contract for RMI calls.
 */

public interface RMIInterface extends Remote {

	@RequiresRole("Admin")
	public boolean createItem(SessionInterface roleType, Integer id, String itemName,
			String itemDescription, String itemType, Integer itemPrice,
			Integer quantityAvailable) throws RemoteException, InterruptedException, Exception;

	@RequiresRole("Admin")
	public boolean updateItem(SessionInterface roleType, Integer id, String itemName,
			String itemDescription, String itemType, Integer itemPrice,
			Integer quantityAvailable) throws RemoteException, InterruptedException;
	
	@RequiresRole("Admin")
	public boolean removeItem(SessionInterface roleType, Integer id) throws RemoteException, InterruptedException;
	
	@RequiresRole("Admin")
	public boolean removeUser(SessionInterface roleType, String userName)
			throws RemoteException, InterruptedException;

	@RequiresRole("Customer")
	boolean checkout(SessionInterface roleType, List<Item> itemList)
			throws RemoteException, Exception;
	
	public List<Item> getItemList() throws RemoteException, InterruptedException;
	
	public SessionInterface getDetails(String userName,String password)throws RemoteException, IOException, InterruptedException;
}
