//EJB 2.0
package examples.value_objects;

import java.rmi.RemoteException;
import javax.ejb.CreateException;
import javax.ejb.EJBHome;
import javax.ejb.FinderException;

public interface OrderHome extends EJBHome{
	public Order create(long orderID, String customerName, AddressValueObject shipAddress)
	//public Order create(long orderID, String customerName)	
		throws RemoteException, CreateException;
	public Order findByPrimaryKey(Long orderID)
		throws RemoteException, FinderException;
}