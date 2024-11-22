package banking;
import javax.ejb.*;
import java.rmi.*;
public interface AccountManager extends EJBObject
{
	public void deposit(String customerName, double amount) throws RemoteException;
	public double withdraw(String customerName, double amount) throws RemoteException, InsufficientFundsException;
	public double getBalance(String customerName) throws RemoteException;
	public double calculateInterest(String customerName) throws RemoteException;
	public Account createAccount(String customerName, double initialBalance) throws NoAccountCreatedException, RemoteException;
	public void removeAccount(String customerName) throws NoAccountCreatedException, RemoteException;
}