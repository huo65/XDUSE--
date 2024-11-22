package banking;
import javax.ejb.*;
import java.rmi.*;
public interface Account extends EJBObject
{
	public double  getBalance() throws RemoteException;
	public void deposit(double amount) throws RemoteException;
	public double withdraw(double amount) throws RemoteException, InsufficientFundsException;
	public double calculateInterest() throws RemoteException;
}