public class Client
{
	public static void main(String[] args)
	{
		org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args, null);
    		
           	Bank.AccountManager manager = Bank.AccountManagerHelper.bind(
      			orb, "/DefaultServantBankPOA", "Zhang3".getBytes());
    		Bank.Account account = manager.open("1088-Account");
    		System.out.println("1088-Account的余额为:"+ 
			account.getBalance() + "元。");
    		
		Bank.AccountManager manager1 = Bank.AccountManagerHelper.bind(
      			orb, "/DefaultServantBankPOA", "Li4".getBytes());
    		account = manager1.open("1088-Account");
    		System.out.println("1088-Account的余额为:" + 
			account.getBalance() + "元。");
	}
}
			