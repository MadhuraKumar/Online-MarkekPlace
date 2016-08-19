import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.Callable;
import com.mysql.jdbc.Connection;

/**
 * This class provides method to establish a database connection
 * using future pattern.
 * 
 * @author Madhura
 *
 */


public class DBConn implements Callable<Object> {

	private static Connection conn;
	
	@Override
	public Connection call() throws Exception {
		
		if(conn == null){
			try{
				String hostname = "in-csci-rrpc01.cs.iupui.edu:3306";
				String dbName = "kumarma_db";
				
				String url = "jdbc:mysql://" + hostname + "/" + dbName;
				String username = "kumarma";
				String password = "kumarma";
	
				System.out.println("Connecting database...");
	
				conn = (Connection) DriverManager.getConnection(url, username, password);
						
				System.out.println("Database connected! \n");
				} catch (SQLException e) {
				    throw new IllegalStateException("Cannot connect the database!", e);
				}
		}
		return conn;
		
	}
}
