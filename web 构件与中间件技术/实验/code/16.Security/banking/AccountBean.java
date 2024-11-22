package banking;

import javax.ejb.*;
import javax.naming.*;

public abstract class AccountBean implements EntityBean{
	private EntityContext context;
	private static final double INTEREST = 0.01;
	public AccountBean() {}
	//CMP fields
	public abstract double getCurrentBalance();
	public abstract void setCurrentBalance(double orderID);
	
	public abstract String getCustomerName();
	public abstract void setCustomerName(String customerName);
	
	//business method
	public double getBalance(){
		return getCurrentBalance();
	}
	
	public void deposit(double amount) {
		setCurrentBalance(getCurrentBalance() + amount);
	}
	public double withdraw(double amount) throws InsufficientFundsException {
		if(getCurrentBalance() < amount){
			throw new InsufficientFundsException("Account does not have " + amount);
		}else{
			setCurrentBalance(getCurrentBalance() - amount);
			return getCurrentBalance();
		}
	}
	public double calculateInterest() {
		setCurrentBalance(getCurrentBalance() + (getCurrentBalance() * INTEREST));
		return getCurrentBalance();
	}
	
	public String ejbCreate(String customerName, double currentBalance)
			throws CreateException{
		setCustomerName(customerName);
		setCurrentBalance(currentBalance);
		return null;
	}

	public void ejbPostCreate(String customerName, double currentBalance) {}
	public void ejbActivate() {}
	public void ejbLoad() {}
	public void ejbPassivate() {}
	public void ejbRemove() {}
	public void ejbStore() {}
	public void setEntityContext(EntityContext ctx) {
		context = ctx;	
	}
	public void unsetEntityContext() {
		context = null;
	}
}