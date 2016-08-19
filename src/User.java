import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * This class represents the user POJO.
 * @author Madhura
 *
 */

public class User {
	
	
	private String userName;
	
	private String name;
	
	private String password;
	
	private String userEmail;

	private String role;
	
	public String getName() {
		return name;
	}

	public void setname(String name) {
		this.name = name;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	/**
	 * This method is used to return sessionInterface by authorizing the UserDetails
	 *  
	 * @param userName
	 * @param password
	 * @return
	 * @throws IOException
	 * @throws InterruptedException
	 */
	
	public static SessionInterface getDetails(String userName, String password) throws IOException, InterruptedException{
		
		User user = new User();
		DatabaseWrapper data = DatabaseWrapper.getInstance();
		
				try {
					ResultSet rs = data.getUserDetails();
					
					while (rs.next())  {
				        String userName1 = rs.getString("UserName");
				        String name1 = rs.getString("Name");
				        String password1 = rs.getString("Password");
				        String role1 = rs.getString("Role");
				        if(userName1.equalsIgnoreCase(userName)&& password1.equalsIgnoreCase(password)){
							user.setname(name1);
							user.setRole(role1);
							return new SessionInterfaceImpl(user.getName(),user.getRole());
				        }
					}
				} catch (SQLException e) {
					System.err.println("Unable execute query!");
					e.printStackTrace();
				}
	
		return null;
	}
}
