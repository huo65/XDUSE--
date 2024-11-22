public class AccountImpl
	extends Bank.AccountPOA
{
	protected float balance;
	
	public AccountImpl(float bal)
	{
		balance = bal;
	}
	
	public void deposit(float amount)
	{
		balance += amount;
	}
	
	public boolean withdraw(float amount)
	{
		if(balance < amount)
			return false;
		else{
			balance -= amount;
			return true;
		}
	}
	
	public float getBalance()
	{
		return balance;
	}
}