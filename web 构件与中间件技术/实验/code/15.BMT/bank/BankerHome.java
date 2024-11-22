package bank;

import java.rmi.RemoteException;
import javax.ejb.*;

public interface BankerHome extends EJBHome
{
	public Banker create() throws RemoteException, CreateException;
}
	