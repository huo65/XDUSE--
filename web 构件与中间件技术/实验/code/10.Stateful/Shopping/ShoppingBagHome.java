package Shopping;

import javax.ejb.*;
public interface ShoppingBagHome extends EJBHome
{
	public ShoppingBag create(String customerName) throws java.rmi.RemoteException, javax.ejb.CreateException;//, UserException2;
}
	