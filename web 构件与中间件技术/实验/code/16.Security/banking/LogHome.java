package banking;

import java.rmi.RemoteException;
import javax.ejb.*;

public interface LogHome extends EJBHome
{
	public Log create(String msg) 
		throws RemoteException, CreateException;
	public Log findByPrimaryKey(String key)
		throws RemoteException, FinderException;
}
	