import java.rmi.RemoteException;


/**
 * Dispatcher class
 * @author Madhura
 *
 */
public class Dispatcher {
      //Concrete Views
	private AdminView adminView;
	private CustomerView customerView;
	
	
	/**
	 * Dispatcher constructor
	 */
	
	public Dispatcher(ClientController cc){
		adminView = new AdminView(cc);
		customerView = new CustomerView(cc);
	}
	
	/**
	 * Dispatching the view based on the request
	 * 
	 * @param request
	 * @throws RemoteException 
	 * @throws InterruptedException 
	 */
	
	public void dispatch(String request) throws RemoteException, InterruptedException{
		if(request.equalsIgnoreCase("Admin")){
			adminView.showView();
		}
		else {
			customerView.showView();
		}
	}
	
}
