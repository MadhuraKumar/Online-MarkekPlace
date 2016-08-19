Name: Madhura Kumar
Object-Oriented Design and Programming Assignment 1
----------------------------------------------------------------------------------------------------------------------------------

How to execute:
-----------------------------------------------------------------
1.  For compiling code
	make

2.  For removing all class files
	make clean

3.  To start server on the server machine
	make run_server
	OR
	java RemoteServerMain
	
4.  To start client on the client machine
	make run_client
	OR
	java ClientMain localhost


Execution:
-----------------------------------------------------------------

1. Start server first on the server machine (Command prompt ->    pegasus)

 --> make run_server

2. Start client(driver program) with the server ip (localhost) on the client machine 
 
 --> make run_client

The driver program makes sample calls to the given actions i.e
->User signIn or signUp
->Browsing Items
->Updating Items
->Adding Items
->Removing Items
->Purchasing Items (adding to cart)

The driver program has sample inputs hardcoded for each of the above actions which calls the server methods remotely and displays the output.

To check for other input values you need to make changes in the driver program for each functions.

----------------------------------------------------------------------------------------------------------------------------------

Following files are there in the directory:

1. Address.java
   This class holds address information for checkout of items purchased.

2. Cart.java
   This class contains the cart information for checkout which accepts credit card information as of now.

3. CheckoutHelper.java
   This class contains different checkout related activities.

4. ClientMain.java
   This class contains sample test RMI methods implemented in the Server.

5. Constants.java
   This file contains constants shared between client and application.

6. CreditCard.java
   This class contains credit card information required for checkout.

7. Items.java
   This class contains information about Items.

8. RemoteServerImpl.java
   This class contains implementation of all methods defined in RMI interface.

9. RemoteServerMain.java
   This class contains main method for server side.

10. RMIInterface.java
    This is a interface class shared between client and server.

11. User.java
    This class contains user information for signIn or signUp.

12. makefile
    File to compile the code.

13. Readme.txt
    File to introduce directories and files.













