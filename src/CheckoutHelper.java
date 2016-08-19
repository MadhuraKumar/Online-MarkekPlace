import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;


/**
 * This method is used as a helper class 
 * for different checkout related activities.
 * @author Madhura
 *
 */

public class CheckoutHelper {
	
	/**
	 * This method is used to check if the Quantity requested for each item in list
	 * is a valid quantity
	 *  
	 * @param itemList
	 * @throws Exception
	 */

	 boolean checkIfItemSuffice(List<Item> itemList) throws Exception{
		
		DatabaseWrapper data = DatabaseWrapper.getInstance();
		
			Iterator<Item> requestIterator = itemList.iterator();
			while (requestIterator.hasNext()) {
				Item itemObj = requestIterator.next();
				int id = itemObj.getId();
				int quantReq = itemObj.getQuantityAvailable();
				
				try {
					
						ResultSet rs = data.getItems();
						while (rs.next()){
							int id1 = rs.getInt("Id");
							if(id1 == id){
								int quantAvail = rs.getInt("QuantityAvailable");
								if(quantAvail >= quantReq)
								{
									return true;
								}
								else{
									throw new QuantityCheckException(quantAvail);
								}
							}		
						}
						System.out.println("Item does not exist/Cant be purchased");					
						
					} catch (SQLException e1) {
							System.err.println("Unable to create SQL statement!");
							e1.printStackTrace();
					}
			}
		return false;
	}
	
	/**
	 * This method updates the Item table by reducing the quantity
	 * for every valid purchase
	 * 
	 * @param itemList
	 * @throws Exception
	 */
	 
	synchronized boolean updateItemList(List<Item> itemList) throws Exception{
		
		DatabaseWrapper data = DatabaseWrapper.getInstance();
		if (checkIfItemSuffice(itemList)) {
			Iterator<Item> requestIterator = itemList.iterator();
			
			while (requestIterator.hasNext()) {
				Item itemObj = requestIterator.next();
				int id = itemObj.getId();
				int quantReq = itemObj.getQuantityAvailable();
				try{
					       // Gets the item  from the Item table where id matches
					
							ResultSet rs = data.selectQuant(id);
					        while(rs.next()){
						        int quantityAvailable = rs.getInt("QuantityAvailable");
						        int newQuant = quantityAvailable - quantReq;
						        if(data.purchaseItem(id, newQuant)){
							        Item item = new Item(id,null,null,null,null,newQuant);
							    	RemoteServerMain.itemList.put(id,item);
							    	return true;
						        }
						        else{
						        	//Returns false if no item match is found
						        	return false;
						        }
					        }
			        
				} catch (SQLException e1) {
					System.err.println("Unable to create SQL statement!");
					e1.printStackTrace();
				}
			}
		}
		return false;

	}

				
	
}
