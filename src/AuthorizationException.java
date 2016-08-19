/**
 * @author Madhura 
 * This class create a custom exception.
 *
 */
public class AuthorizationException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	private String name;
	
	public AuthorizationException(String name){
		super();
		this.name = name;
	}
	
	@Override
	public String getMessage(){
		return "Invalid Authorization - Access Denied to " + name + "() "
				+ "function!";
	}
	
	public String getName(){
		return name;
	}

}
