import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;




/**
 * This class is used as a client stub to test RMI methods implemented
 * 
 * @author Madhura
 *
 */
public class ClientController {
	/**
	 * This is the controller method of the client
	 * 
	 */
	
	private ClientView view; 
	
	RMIInterface remote = null;
	private SessionInterface sc;
	
	public SessionInterface getSc() {
		return sc;
	}

	public void setSc(SessionInterface sc) {
		this.sc = sc;
	}

	public ClientController(ClientView view){
		this.view = view;
		
	}
	
	/**
	 * This method is used to establish a connection with the server
	 * 
	 */
	
	public RMIInterface connectServer(){
		
		try {
			String hostAddress = "localhost";
			System.out.println("Connecting to server...");
			Registry registry = LocateRegistry.getRegistry(hostAddress,
					Constants.RMI_PORT);
			// Get remote with rm id lookup
			remote = (RMIInterface) registry.lookup(Constants.RMI_ID);
			System.out.println("Server connected! \n");
			
			
		} catch (Exception e) {
			System.out.println("Invalid server or server not started");
			e.printStackTrace();
			
		}
		return remote;
	}
	
	/**
	 * This method is used to get the userDetails from the for every login
	 * 
	 * @throws RemoteException
	 * @throws InterruptedException 
	 */
	
	public SessionInterface getDetails(String userName, String password) throws RemoteException, IOException, InterruptedException{
		
		connectServer();
		return remote.getDetails(userName, password);
	
		
	}
	/**
	 * This method is used to call checkout method which is used to purchase the
	 * item details into the cart
	 * 
	 * @throws RemoteException
	 */
	
	
	public void testPurchase(Integer id, Integer quantity )
			throws RemoteException{
		try{
			System.out.println("------------------------------------------");
			System.out.println("Purchasing Items");
			System.out.println("------------------------------------------");
			List<Item> itemList = new ArrayList<Item>();
			itemList.add(new Item(id, null, null, null, null, quantity));
	
			System.out.println(remote.checkout(this.sc,itemList));
			System.out.println("------------------------------------------");
			System.out.println("Getting item List to reflect changes");
			System.out.println("------------------------------------------");
			browseItems(remote.getItemList());
			
		}catch(InvocationTargetException e) {
			System.out.println(e.getCause());
		}
		catch(Exception e){
			System.out.println("Client Exception: " + e.getMessage());
		}	
	}
	

	/**
	 * This method is used to test remove item functionality
	 * 
	 * @throws RemoteException
	 */
	
	public void testRemoveItem(Integer id)
			throws RemoteException {
		try{
			
			System.out.println(remote.removeItem(this.sc, id));
			System.out.println("------------------------------------------");
			System.out.println("Getting item List to reflect changes");
			System.out.println("------------------------------------------");
			browseItems(remote.getItemList());
		}catch(Exception e){
			System.out.println("Client Exception: " + e.getMessage());
		}
	}

	/**
	 * This method is used to update an Item. The contract between client and
	 * server is to send all details to update. Selecting patch is not accepted.
	 * 
	 * @throws RemoteException
	 */
	
	public void testUpdateItem(Integer id, String itemName, String itemDescription, String itemType, Integer itemPrice,
			Integer quantityAvailable) throws RemoteException {
		
		try{
			System.out.println(remote.updateItem(this.sc, id, itemName, itemDescription, itemType, itemPrice,
						quantityAvailable));
			System.out.println("------------------------------------------");
			System.out.println("Getting item List to reflect changes");
			System.out.println("------------------------------------------");
				browseItems(remote.getItemList());
		}catch(Exception e){
			System.out.println("Client Exception: " + e.getMessage());
		}
	}

	/**
	 * This method is used to add an item and also display list of all items
	 * added.
	 * 
	 * @throws RemoteException
	 */
	
	public void testAddItem(Integer id, String itemName, String itemDescription, String itemType, Integer itemPrice,
			Integer quantityAvailable) throws RemoteException {
			
		try{
		    
			System.out.println(remote.createItem(this.sc, id, itemName, itemDescription, itemType, itemPrice,
					quantityAvailable));
			System.out.println("------------------------------------------");
			System.out.println("Getting item List to reflect changes");
			System.out.println("------------------------------------------");
			browseItems(remote.getItemList());
			
			}catch(InvocationTargetException e) {
				System.out.println("Duplicate Item Inserted");
				System.out.println(e.getCause());
			}catch(Exception e){
				System.out.println("Client Exception: " + e.getMessage());
		}

	}
	
	/**
	 * This method is used display list of all items
	 * added.
	 * 
	 * @throws RemoteException
	 * @throws InterruptedException 
	 */
	
	public void testBrowse() throws RemoteException, InterruptedException{
		List<Item> list = remote.getItemList();
		if(list == null){
			System.out.println("List empty");
		}
		else{
			browseItems(list);
		}
	}
	
	
	/**
	 * This method takes the responsibility of printing from array list
	 * to console
	 * @param itemList
	 */
	
	public void browseItems(List<Item> itemList) {
		Iterator<Item> iterator = itemList.iterator();
		Item item;
		while (iterator.hasNext()) {
			item = iterator.next();
			System.out.println("Id : " + item.getId() + ", " + "Item Name : "
					+ item.getItemName() + ", " + "Item Description : "
					+ item.getItemDescription() + ", Item Type : "
					+ item.getItemType() + ", Item Price : "
					+ item.getItemPrice() + ", Item Quantity : "
					+ item.getQuantityAvailable());
		}
	}
	
	/**
	 * This method takes the responsibility of removing User from the table
	 * to console
	 * 
	 * @param userName
	 * @throws RemoteException 
	 */
	
	public void testRemoveUser(String userName) throws RemoteException{
		
		try{		    
			System.out.println(remote.removeUser(this.sc, userName));
			}catch(Exception e){
				System.out.println("Client Exception: " + e.getMessage());
			}
		
	}
	

}
