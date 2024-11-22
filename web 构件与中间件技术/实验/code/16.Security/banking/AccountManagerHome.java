package banking;

import java.rmi.RemoteException;
import javax.ejb.*;

public interface AccountManagerHome extends EJBHome
{
	public AccountManager create() throws RemoteException, CreateException;
}
	