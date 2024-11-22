package Shopping;

import javax.ejb.*;
import java.rmi.*;
public interface ShoppingBag extends EJBObject
{
	public void addCom(Commodity comm) throws RemoteException;
	public void removeComm(Commodity comm) throws RemoteException, NoSuchCommodityException;
	public Commodity find(String commID) throws RemoteException, NoSuchCommodityException;
	public void clearBag() throws RemoteException;
	public void commit() throws RemoteException, BagEmptyException;
}