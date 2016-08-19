import java.rmi.RemoteException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Generic customer view
 * @author Madhura
 * 
 */
public class CustomerView {
	// Generic method for displaying customer view
	private ClientController cc;
	public static final String LINEDEMARKER = "--------------------------------------------------------------------------";

	
	public CustomerView (ClientController cc){
		this.cc = cc;
	}
	
	public void showView() throws RemoteException, InterruptedException{
		System.out.println("Welcome to Customer page!!");
		CustomerView.provideUserChoice(cc);
			
		}
	
	/**
	 * This class provides user choice at the customer page
	 * 
	 * @param cc
	 * @throws RemoteException
	 * @throws InterruptedException
	 */
	
	public static void provideUserChoice(ClientController cc) throws RemoteException, InterruptedException{
		int choice = 0;
		
        Scanner scanner = new Scanner(System.in);
		while(choice != 3)
	    {
			System.out.println();
			System.out.println("Enter the options below by choosing option number:");
			System.out.println(LINEDEMARKER);
			System.out.println("1. Browse Item");
			System.out.println("2. Purchase Item");
			System.out.println("3. Exit");
			System.out.println();
			choice = scanner.nextInt();
		
				switch (choice) {
						 
						case 1:{
							System.out.println("Getting item List for browse functionality");
							cc.testBrowse();
							break;
						}
						
						case 2:{
								try{
									System.out.println("Enter the item id to purchase");
									int id = scanner.nextInt();
									System.out.println("Enter the quantity");
								    int quant = scanner.nextInt();
								    cc.testPurchase(id, quant);
								}catch(InputMismatchException e){
									System.out.println("Please enter values between 0-9");
									scanner.next();
									continue;
								}
					
							break;
						}
						
						case 3:{
							System.out.println("Exit");
							break;
						}
						default: {
							System.out.println("Incorrect choice");
							break;
				
						}
				}	
	    }
	}


}
