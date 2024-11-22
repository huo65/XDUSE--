package banking;
import javax.ejb.*;
import java.rmi.*;
public interface Log extends EJBObject
{
	public void log(String msg) throws RemoteException;
}