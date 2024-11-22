package banking;

import java.rmi.RemoteException;
import javax.ejb.*;

public interface AccountHome extends EJBHome
{
	public Account create(String customerName, double currentBalance) 
		throws RemoteException, CreateException;
	public Account findByPrimaryKey(String key)
		throws RemoteException, FinderException;
}
	