import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class implements the interface shared.
 * This has the implementation for all the methods used.
 */

public class RemoteServerImpl extends UnicastRemoteObject implements
		RMIInterface {

	private static final long serialVersionUID = 1L;

	protected RemoteServerImpl() throws RemoteException {
		super();
	}

	/**
	 * This method is used for removing the user.
	 * 
	 * @param userName
	 * @throws RemoteException
	 * @throws InterruptedException 
	 */
	
	
	@Override
	public synchronized boolean removeUser(SessionInterface roleType, String userName)
			throws RemoteException, InterruptedException {
		   
			DatabaseWrapper data = DatabaseWrapper.getInstance();
				if (userName == null){
					return false;
				}
				try{		
						ResultSet rs = data.getUserDetails();
						//rs.next();
						while (rs.next()){
							String uname = rs.getString("UserName");
							String role = rs.getString("Role");
							if(uname.equals(userName) && !(role.equalsIgnoreCase("admin"))){
								if(data.removeUser(userName)){
											return true;
								}
								else{
									return false;
								}
			
							}
						}
							
						System.out.println("User does not exist / cannot be removed.");
				} catch (SQLException e1) {
					System.err.println("Unable to create SQL statement!");
					e1.printStackTrace();
				}
		
				return false;		
								
			
	}
	
	@Override
	public synchronized boolean createItem(SessionInterface roleType, Integer id, String itemName,
			String itemDescription, String itemType, Integer itemPrice,
			Integer quantityAvailable) throws RemoteException, InterruptedException, Exception {
		
		DatabaseWrapper data = DatabaseWrapper.getInstance();
				
					if (itemName.isEmpty() || itemPrice == 0 || id == 0
							|| quantityAvailable == 0) {
						System.out.println("Item name is empty");
						return false;
						}
			
					try{
						
						if(data.insertItem(id, itemName, itemDescription, itemType, itemPrice, quantityAvailable)){
							return true;
						}
						else{
							return false;
						} 
						
					} catch (SQLException e) {
						throw e;
					}	
		
	}

	@Override
	public synchronized List<Item> getItemList() throws RemoteException, InterruptedException {

			DatabaseWrapper data = DatabaseWrapper.getInstance();
			try{
					ResultSet rs = data.getItems();
					
					while (rs.next())  {
				        int id = rs.getInt("Id");
				        String itemName = rs.getString("ItemName");
				        String itemDescription = rs.getString("ItemDescription");
				        String itemType = rs.getString("ItemType");
				        int itemPrice = rs.getInt("ItemPrice");
				        int quantityAvailable = rs.getInt("QuantityAvailable");
				        Item item = new Item(id, itemName, itemDescription, itemType,
				    					itemPrice, quantityAvailable);
				        
				        synchronized(RemoteServerMain.itemList){
				        	RemoteServerMain.itemList.put(id, item);
				        }
					}
					return new ArrayList<Item>(RemoteServerMain.itemList.values());
					
				
			} catch (SQLException e1) {
				System.err.println("Unable to create SQL statement!");
				e1.printStackTrace();
			}
	
		return null;
		
	}
	
	@Override
	public synchronized boolean updateItem(SessionInterface roleType, Integer id, String itemName,
			String itemDescription, String itemType, Integer itemPrice,
			Integer quantityAvailable) throws RemoteException, InterruptedException {
		
		DatabaseWrapper data = DatabaseWrapper.getInstance();
		
		     if (itemName.isEmpty() || itemPrice == 0 || id == 0
					|| quantityAvailable == 0) {
				System.out.println("Item name is empty");
				return false;
			}
					
			try{
					ResultSet rs = data.getItems();
					while (rs.next()){
						int id1 = rs.getInt("Id");
						if(id1 == id){
							if(data.updateItem(id, itemName, itemDescription, itemType, itemPrice, quantityAvailable)){
								return true;
							}
							else{
								return false;
							}
						}		
					}
					
					System.out.println("Item does not exist");
				
			} catch (SQLException e1) {
				System.err.println("Unable to create SQL statement!");
				e1.printStackTrace();
			}
		 
		return false;
		
	}

	@Override
	public synchronized boolean removeItem(SessionInterface roleType, Integer id) throws RemoteException, InterruptedException {
		
		DatabaseWrapper data = DatabaseWrapper.getInstance();
					if (id == 0){
						return false;
					}
					
			try{		
					ResultSet rs = data.getItems();
					
					while (rs.next()){
						int id1 = rs.getInt("Id");
						if(id1 == id){
							if(data.removeItem(id)){
								
								synchronized(RemoteServerMain.itemList){
									RemoteServerMain.itemList.remove(id);
								}
						        return true;
							}
							else{
								return false;
							}
						}

					}
					System.out.println("Item does not exist");
				
			} catch (SQLException e1) {
				System.err.println("Unable to create SQL statement!");
				e1.printStackTrace();
			}
			
		return false;
		
	}

	@Override
	public synchronized boolean checkout(SessionInterface roleType, List<Item> itemList)
			throws RemoteException, Exception{
		try {
			CheckoutHelper checkoutHelper = new CheckoutHelper();
			if(checkoutHelper.updateItemList(itemList)){
				return true;
			}		
		} catch (Exception e) { 
			throw e;
		}
		return false;
	}
	
	@Override
	public synchronized SessionInterface getDetails(String userName,String password) throws IOException,RemoteException, InterruptedException {
		
		SessionInterface sc = User.getDetails(userName,password);
		if(sc != null){
			System.out.println("User " + sc.getSessionID() + " connected to server");
		}
		return sc;
	}

}
