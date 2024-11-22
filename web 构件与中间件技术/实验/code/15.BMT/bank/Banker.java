package bank;
import javax.ejb.*;
import java.rmi.*;
public interface Banker extends EJBObject
{
	public void deposit(String accountName, int amount) throws RemoteException, BankerFailureException;
	public void withdraw(String accountName, int amount) throws RemoteException, BankerFailureException;
	public int getBalance(String accountName) throws RemoteException, BankerFailureException;
}