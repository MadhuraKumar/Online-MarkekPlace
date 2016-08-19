import java.lang.reflect.Proxy;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.Map;

/**
 * This is the main method for server side.
 * It takes care running of server
 */

public class RemoteServerMain {

	//public static Map<String, User> userList = new HashMap<String, User>();
	public static Map<Integer,Item> itemList = new HashMap<Integer,Item>();
	 
	public static void main(String[] args) throws RemoteException, AlreadyBoundException {

		try{
		Registry registry = LocateRegistry.createRegistry(Constants.RMI_PORT);
		
		RMIInterface assignment = (RMIInterface) Proxy.newProxyInstance(RMIInterface.class.getClassLoader(),
                new Class<?>[] {RMIInterface.class},
                new AuthorizationInvocationHandler(new RemoteServerImpl()));
		
		registry.bind(Constants.RMI_ID, assignment);
		System.out.println("Server started:");
		}catch (Exception e){
			System.out.println("Server Exception: " + e.getMessage());
			e.printStackTrace();
		}
		
	}
	
}
