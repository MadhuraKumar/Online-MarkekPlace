import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

/**
 * This class provides a generic view for both 
 * type of users
 * 
 * @author Madhura
 *
 */

public class ClientView {
	
	
	
	public static void main(String[] args) throws NotBoundException, IOException, InterruptedException{
		
		ClientView view = new ClientView();
		ClientController controller = new ClientController(view);

		view.testFrontController(controller);
		
	
}

	public void testFrontController(ClientController controller) throws RemoteException, IOException, NotBoundException, InterruptedException{
	
		Scanner scanner = new Scanner(System.in);
		ClientView view = new ClientView();
		FrontController fc = new FrontController(controller, view);
		System.out.println("Enter Username");
		String username = scanner.nextLine();
		System.out.println("Enter Password");
		String password = scanner.nextLine();
		fc.userName = username;
		fc.password = password;
		String request = "";
		fc.dispatchRequest(request);
		
	}

}