//The code is used to create the remote interface of an enterprise bean
//used by the HelloApp application
import javax.ejb.*;
import java.rmi.*;
public interface Hello extends EJBObject
{
	String sayHello(String MyName) throws RemoteException;//, UserException1;
}