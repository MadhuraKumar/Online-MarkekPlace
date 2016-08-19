import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * FrontController class which handles access controller through
 * the pattern for every user login
 * 
 * @author Madhura
 *
 */

public class FrontController {
	// Dispatcher instance...
		private Dispatcher dispatcher;
		public String userName;
		public String password;
		private String request;
		private ClientController controller;
		private SessionInterface sc;
		private RMIInterface remote;

		/**
		 * Front Controller Constructor
		 */
		public FrontController(ClientController cc, ClientView view) {
			this.controller = cc;
			dispatcher = new Dispatcher(this.controller);
		}

		/**
		 * Attempt to authentic user login.
		 * 
		 * @return T/F
		 * @throws IOException 
		 * @throws RemoteException 
		 * @throws NotBoundException 
		 * @throws InterruptedException 
		 * 
		 */
		private boolean isAuthenticUser() throws RemoteException, IOException, NotBoundException, InterruptedException {

				SessionInterface sc = this.controller.getDetails(this.userName,this.password);
				if(sc != null){
					this.sc = sc;
					System.out.println("User is authenticated successfully.");
					System.out.println("User Session ID is: " + (sc.getSessionID()));
					return true;
				}
				
			

				System.out.println("Invalid User/User does not exist");
			return false;
			
		}

		/**
		 * Responsible for dispatching the request to the Dispatcher.
		 * 
		 * @param request
		 * @throws IOException 
		 * @throws RemoteException 
		 * @throws NotBoundException 
		 * @throws InterruptedException 
		 */
		public void dispatchRequest(String request) throws RemoteException, IOException, NotBoundException, InterruptedException{
			
			System.out.println("Page Requested: " + request);   
			
			// If the user has been authenticated - dispatch request...
			if(isAuthenticUser()) {
				request = this.request;
				this.controller.setSc(this.sc);
				dispatcher.dispatch(this.sc.getRole());
				
		    }	
		}


}
