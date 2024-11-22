//EJB 2.0
package examples.local_objects;

import java.rmi.RemoteException;
import javax.ejb.EJBObject;

public interface Order extends EJBObject{
	public String getCustomerName() throws RemoteException;
	public AddressValueObject getShipAddressView() throws RemoteException;
}