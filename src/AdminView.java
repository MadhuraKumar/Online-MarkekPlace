import java.rmi.RemoteException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Generic Admin view
 * @author Madhura
 * 
 */
public class AdminView {
	// Generic method for displaying admin view
	private ClientController cc;
	public static final String LINEDEMARKER = "--------------------------------------------------------------------------";

	
	public AdminView (ClientController cc){
		this.cc = cc;
	}
	
	public void showView() throws RemoteException, InterruptedException{
		
		System.out.println("Welcome to Admin page!!");
		AdminView.provideUserChoice(cc);
	}
	
	/**
	 * This method provides the user choice at the Admin page
	 * 
	 * @param cc
	 * @throws RemoteException
	 * @throws InterruptedException
	 */
	
	public static void provideUserChoice(ClientController cc) throws RemoteException, InterruptedException{
		int choice = 0;
		
        Scanner scanner = new Scanner(System.in);
		while(choice != 6)
	    {
			System.out.println();
			System.out.println("Enter the options below by choosing option number:");
			System.out.println(LINEDEMARKER);
			System.out.println("1. Add Item");
			System.out.println("2. Browse Item");
			System.out.println("3. Update Item");
			System.out.println("4. Remove Item");
			System.out.println("5. Remove User");
			System.out.println("6. Exit");
			System.out.println();
			choice = scanner.nextInt();
		
				switch (choice) {
						case 1: {
							try{
								System.out.println("Enter item id");
								int id = scanner.nextInt();
								System.out.println("Enter item name");
								scanner.nextLine();
								String name = scanner.nextLine();
								System.out.println("Enter item discription");
								String desc = scanner.nextLine();
								System.out.println("Enter item type");
								String type = scanner.nextLine();
								System.out.println("Enter price");
								int price = scanner.nextInt();
								System.out.println("Enter quantity");
								int quant = scanner.nextInt();
								System.out.println("------------------------------------------");
								System.out.println("Adding new item");
								System.out.println("------------------------------------------");
								cc.testAddItem(id, name, desc, type, price,
										quant);
							}catch(InputMismatchException e){
								System.out.println("Please enter values between 0-9");
								scanner.next();
								continue;
							}
						
							break;
							
						}
						 
						case 2:{
							System.out.println("Getting item List for browse functionality");
							cc.testBrowse();
							break;
						}
						
						case 3:{
							try{
							
								System.out.println("Enter item id to update");
								int id = scanner.nextInt();
								System.out.println("Update item name");
								scanner.nextLine();
								String name = scanner.nextLine();
								System.out.println("Update item discription");
								String desc = scanner.nextLine();
								System.out.println("Update item type");
								String type = scanner.nextLine();
								System.out.println("Update price");
								int price = scanner.nextInt();
								System.out.println("Update quantity");
								int quant = scanner.nextInt();
								System.out.println("------------------------------------------");
								System.out.println("Updating item 1 description");
								System.out.println("------------------------------------------");
								cc.testUpdateItem(id, name, desc, type, price,
										quant);
							}catch(InputMismatchException e){
								System.out.println("Please enter values between 0-9");
								scanner.next();
								continue;
							}
						
							break;
						}
						
						case 4:{
							try{
							
								System.out.println("Enter the item id to remove");
								int id = scanner.nextInt();
								System.out.println("------------------------------------------");
								System.out.println("Removing item from item list");
								System.out.println("------------------------------------------");
								cc.testRemoveItem(id);
							}catch(InputMismatchException e){
								System.out.println("Please enter values between 0-9");
								scanner.next();
								continue;
							}
						
							break;
						}
						
						case 5:{
							
							System.out.println("Enter the UserName to remove");
							scanner.nextLine();
							String userName = scanner.nextLine();
							System.out.println("------------------------------------------");
							System.out.println("Removing User");
							System.out.println("------------------------------------------");
							cc.testRemoveUser(userName);
							break;
						}
						
						case 6:{
							
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
