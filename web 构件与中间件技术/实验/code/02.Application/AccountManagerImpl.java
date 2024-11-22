import java.util.*;
import org.omg.PortableServer.*;

public class AccountManagerImpl
	extends Bank.AccountManagerPOA
{
	protected Hashtable accountList;
	
	public AccountManagerImpl()
	{
		accountList = new Hashtable();
	}
	
	public synchronized Bank.Account open(String name)
	{
		Bank.Account account = (Bank.Account)accountList.get(name);
		
		if(account == null){
			//随即初始化一个0到1000之间的金额
			
			float balance = 0;
			AccountImpl accountServant = new AccountImpl(balance);
			try{
				//用缺省的POA激活对象，这里缺省的POA就是根POA
				org.omg.CORBA.Object obj = 
					_default_POA().servant_to_reference(accountServant);
				//将对象引用窄化为Account类型
				account = Bank.AccountHelper.narrow(obj);
			}catch(Exception exc){
				exc.printStackTrace();
			}
			
			accountList.put(name, account);
			System.out.println("新开账户：" + name);
		}
		
		return account;
	}
}			