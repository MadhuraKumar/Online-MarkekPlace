JFLAGS = -g
JC = javac
JVM= java 
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLIENT_MAIN = ClientMain
SERVER_MAIN = RemoteServerMain

CLASSES = ClientMain.java\
	Constants.java\
	RemoteServerImpl.java\
	RemoteServerMain.java\
	RMIInterface.java\
	Address.java\
	Cart.java\
	CheckoutHelper.java\
	CreditCard.java\
	Item.java\
	User.java\
	
default: classes

classes: $(CLASSES:.java=.class)

run_server: $(SERVER_MAIN).class
	$(JVM) $(SERVER_MAIN)
run_client: $(CLIENT_MAIN).class
	$(JVM) $(CLIENT_MAIN)

clean:
	$(RM) *.class