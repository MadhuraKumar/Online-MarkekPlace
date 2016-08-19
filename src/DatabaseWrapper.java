
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

/**
 * This class is a wrapper class for all database queries
 * by connecting to database using future pattern
 * 
 * @author Madhura
 *
 */

public class DatabaseWrapper {
	
	private static Connection conn;
	private static final ExecutorService threadpool = Executors.newFixedThreadPool(3);
	private static DatabaseWrapper createInstance;
	
	private DatabaseWrapper(){
		//Database connection instance 
		
		DBConn getDBconn = new DBConn();
		System.out.println("Submitting Connection...");

    	// Executor Service makes use of a future through submit() method...
        Future<?> future = threadpool.submit(getDBconn);
        
        System.out.println("Connection submitted");

        // Polling to see if the Future is "ready"...
        while (!future.isDone()) {
            System.out.println("Task is not completed yet....");
            try {
            	
				Thread.sleep(001);  //sleep for 1 millisecond before checking again

				conn = (Connection) future.get();
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }

        System.out.println("Task is completed, let's check result");
        threadpool.shutdown();

	}
	
	/**
	 * This method returns a single instance of the Database using a singleton
	 * pattern
	 * 
	 */
	
	public static DatabaseWrapper getInstance(){
		synchronized(DatabaseWrapper.class){
			if(createInstance == null){
				 createInstance = new DatabaseWrapper();
			}
		}
		return createInstance;
	}
	
	/**
	 * This method is used to return the Users in 
	 * the UserDetails table from the database.
	 * 
	 * @throws InterruptedException
	 */
	
	public ResultSet getUserDetails() throws InterruptedException{
		
		Statement stmt;
		  ResultSet rs = null;
		  try {
			  synchronized(conn){
				   stmt = (Statement) conn.createStatement();
				   rs = stmt.executeQuery("SELECT * FROM UserDetails");
			 }
		  } catch (SQLException e) {
			  System.err.println("Unable execute query!");
			  e.printStackTrace();
		  }
		  return rs;
	}
	
	/**
	 * This method is used to return the Items present 
	 * the Item table from the database.
	 * 
	 * @throws InterruptedException
	 */
	
	public ResultSet getItems() throws InterruptedException{
		Statement stmt;
		  ResultSet rs = null;
		  try {
			  synchronized(conn){
				   stmt = (Statement) conn.createStatement();
				   rs = stmt.executeQuery("SELECT * FROM Item");
			  }
		  } catch (SQLException e) {
			  System.err.println("Unable execute query!");
			  e.printStackTrace();
		  }
		  return rs;
		 }
		
	/**
	 * This method is used to insert an items into 
	 * the Item table.
	 * 
	 * @throws InterruptedException
	 */
	
	public boolean insertItem(Integer id, String itemName,
			String itemDescription, String itemType, Integer itemPrice,
			Integer quantityAvailable) throws InterruptedException, Exception{
		
		  try{
			    synchronized(conn){
					PreparedStatement stmt1 = conn.prepareStatement("INSERT INTO Item (Id,ItemName,ItemDescription,Itemtype,ItemPrice,QuantityAvailable)" + "VALUES (?,?,?,?,?,?)");
					stmt1.setInt(1, id);
					stmt1.setString(2, itemName);
					stmt1.setString(3, itemDescription);
					stmt1.setString(4, itemType);
					stmt1.setInt(5, itemPrice);
					stmt1.setInt(6, quantityAvailable);
					stmt1.execute();
					return true;
			    }
				
		  }catch (SQLException e) {
				System.err.println("Unable execute query!");
				throw e;
			}
		  	
	}
	
	/**
	 * This method is used to remove an User present 
	 * the UserDetsils table.
	 * 
	 * @param userName
	 * @throws InterruptedException
	 */
	
	public boolean removeUser(String userName) throws InterruptedException{
		try{
			synchronized(conn){
				PreparedStatement stmt1 = conn.prepareStatement("DELETE FROM UserDetails WHERE UserName = ?");
				stmt1.setString(1, userName);
				stmt1.execute();
					return true;
			}
		} catch (SQLException e) {
			System.err.println("Unable execute query!");
			e.printStackTrace();
		}
	  return false;
	}
	
	/**
	 * This method is used to update an Items present 
	 * the Item table.
	 * 
	 * @throws InterruptedException
	 */
	
	
	public boolean updateItem(Integer id, String itemName,
			String itemDescription, String itemType, Integer itemPrice,
			Integer quantityAvailable) throws InterruptedException{
		
		try{
			
			synchronized(conn){
				PreparedStatement stmt1 = conn.prepareStatement("UPDATE Item SET ItemName = ?, ItemDescription = ?, Itemtype = ?, ItemPrice = ?, QuantityAvailable = ? WHERE Id = ?");
				stmt1.setString(1, itemName);
				stmt1.setString(2, itemDescription);
				stmt1.setString(3, itemType);
				stmt1.setInt(4, itemPrice);
				stmt1.setInt(5, quantityAvailable);
				stmt1.setInt(6, id);
				if(stmt1.executeUpdate() != 0){
				 return true;
				}
			}
		
		} catch (SQLException e) {
			System.err.println("Unable execute query!");
			e.printStackTrace();
		}
	  return false;
	}
	
	/**
	 * This method is used to remove an Item present 
	 * the Item table.
	 * 
	 *  @param id
	 * @throws InterruptedException
	 */
	
	public boolean removeItem(Integer id) throws InterruptedException{
		
		try{
			synchronized(conn){
				PreparedStatement stmt1 = conn.prepareStatement("DELETE FROM Item WHERE Id = ?");
				stmt1.setInt(1,id);
		        stmt1.execute();
			}
			return true;
			
		
		} catch (SQLException e) {
			System.err.println("Unable execute query!");
			e.printStackTrace();
		}
	  return false;
	}
	
	/**
	 * This method is used to return an Item present 
	 * the Item table.
	 * 
	 * @param id
	 * @throws InterruptedException
	 */
	
	public ResultSet selectQuant(Integer id) throws InterruptedException{
		
		ResultSet rs = null;
		try{
			synchronized(conn){
				PreparedStatement stmt1 = conn.prepareStatement("SELECT QuantityAvailable FROM Item WHERE Id = ?");
				stmt1.setInt(1,id);
		        stmt1.execute();
		        rs = stmt1.getResultSet();
			}
		
		} catch (SQLException e) {
			System.err.println("Unable execute query!");
			e.printStackTrace();
		}
	  return rs;
	}
	
	/**
	 * This method is used to update the quantity in the Item table 
	 * for every valid purchase
	 * 
	 * @param id
	 * @throws InterruptedException
	 */
	
	public boolean purchaseItem(int id, int quant) throws InterruptedException{
		
		try{
			synchronized(conn){
				PreparedStatement stmt = conn.prepareStatement("UPDATE Item SET QuantityAvailable = ? WHERE Id = ?");
				stmt.setInt(1,quant);
				stmt.setInt(2,id);
		        stmt.execute();
			}
			return true;
		
		} catch (SQLException e) {
			System.err.println("Unable execute query!");
			e.printStackTrace();
		}
	  return false;
	}
	
	

}
	 
	


