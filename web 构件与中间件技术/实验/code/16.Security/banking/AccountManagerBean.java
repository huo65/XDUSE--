package banking;

import javax.ejb.*;
import javax.naming.*;
import java.util.*;
import javax.rmi.*;
import java.rmi.RemoteException;

public class AccountManagerBean implements SessionBean
{
	private SessionContext context;
	public static final String DEPOSIT = "deposit funds";
	public static final String WITHDRAW = "withdraw funds";
	public static final String GETBALANCE = "get an account balance";
	public static final String CALCULATEINTEREST = "calculate interest on an account";
	public static final String CREATEACCOUNT = "create an account";
	public static final String CLIENT = "Client";
	public static final String BANK = "Bank";
	
	public AccountManagerBean() {}
	public void ejbRemove() {}
	public void ejbPassivate() {}
	public void ejbActivate() {}
	public void setSessionContext(SessionContext ctx) {
		context = ctx;
	}
	public void ejbCreate() {}

	//business method
	public void deposit(String customerName, double amount){
		logActivity(DEPOSIT, customerName);
		try{
			Account account = getAccount(customerName);
			account.deposit(amount);
		}catch(NoSuchAccountException e){ 
			throw new EJBException();
		}catch(RemoteException ex){
			throw new EJBException();
		}
	}

	public double withdraw(String customerName, double amount) 
			throws InsufficientFundsException{
		double newBal;
		logActivity(WITHDRAW, customerName);
		try{
			Account account = getAccount(customerName);
			double bal = account.getBalance();
			if(amount > bal){
				throw new InsufficientFundsException("Account does not have " + amount);
			}
			newBal = account.withdraw(amount);
		}catch(NoSuchAccountException e){ 
			throw new EJBException();
		}catch(RemoteException ex){
			throw new EJBException();
		}
		return newBal;
	}

	public double getBalance(String customerName){
		System.out.println("Entry getBalance");
		logActivity(GETBALANCE, customerName);
		System.out.println("after logActivity");
		double bal = 0;
		try{
			Account account = getAccount(customerName);
			System.out.println("after getAccount");
			bal = account.getBalance();
			System.out.println("after getBalance");
//		}catch(AccountAccessDeniedException AE){
//			throw new AccountAccessDeniedException("Access Denied");
		}catch(NoSuchAccountException e){ 
			throw new EJBException();
		}catch(RemoteException ex){
			throw new EJBException();
		}
		return bal;
	}		
	public double calculateInterest(String customerName){
		logActivity(CALCULATEINTEREST, customerName);
		try{
			Account account = getAccount(customerName);
			return account.calculateInterest();
		}catch(NoSuchAccountException e){ 
			throw new EJBException();
		}catch(RemoteException ex){
			throw new EJBException();
		}
	}
	public Account createAccount(String customerName, double initialBalance) throws NoAccountCreatedException{
		logActivity(CREATEACCOUNT, customerName);
		try{
			AccountHome accountHome = getAccountHome();
			return accountHome.create(customerName, initialBalance);
		}catch(CreateException ce){
			throw new NoAccountCreatedException(ce.getMessage());
		}catch(RemoteException ex){
			throw new EJBException();
		}
	}
	
	public void removeAccount(String customerName) throws NoAccountCreatedException{
		try{
			Account account = getAccount(customerName);
			account.remove();
		}catch(NoSuchAccountException e){ 
			throw new EJBException();
		}catch(RemoveException re){
			throw new EJBException();
		}catch(RemoteException ex){
			throw new EJBException();
		}
	}

	private void logActivity(String method, String customer){
		this.log(this.context.getCallerPrincipal().getName() + "tried to " + 
			method + " for " + customer);
	}
	
	private Account getAccount(String customerName) throws NoSuchAccountException{
		try{
			System.out.println("Entry getAccount");
			AccountHome accountHome = getAccountHome();
			System.out.println("after getAccountHome");
			Account account = accountHome.findByPrimaryKey(new String(customerName));
			System.out.println("after findByPrimaryKey");
			return account;
		}catch(RemoteException ex){
			throw new EJBException();
		}catch(FinderException fe){
			throw new NoSuchAccountException("No such account");
		}
	}
	
	private AccountHome getAccountHome(){
		try{
			InitialContext initial = new InitialContext();
			Object objref = initial.lookup("java:comp/env/ejb/account");
			AccountHome home = (AccountHome)javax.rmi.PortableRemoteObject.narrow(objref, AccountHome.class);
			return home;
		}catch(NamingException ne){
			throw new EJBException();
		}
	}
		
	private void log(String msg){
		try{
			InitialContext ctx = new InitialContext();
			Object ref = ctx.lookup("java:comp/env/ejb/log");
			LogHome h = (LogHome)javax.rmi.PortableRemoteObject.narrow(ref, LogHome.class);
			h.create(msg);
		}catch(Exception e){}
	}
}