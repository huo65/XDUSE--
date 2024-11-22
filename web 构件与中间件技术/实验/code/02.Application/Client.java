public class Client
{
	public static void main(String[] args)
	{
		//初始化ORB
		org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args, null);
		//利用POA全称与对象标识"BankManager"查找账户管理员
		Bank.AccountManager manager = Bank.AccountManagerHelper.bind(
			orb, "/BankPOA", "BankManager".getBytes());//,"192.168.118.3", new com.inprise.vbroker.CORBA.BindOptions());
		String name = args.length > 0? args[0]:"SHLSong";
		Bank.Account account = manager.open(name);
		
		System.out.println(name + "的账户余额为" + account.getBalance() + "元");
		account.deposit(400);
		System.out.println("存款400元后，余额为" + account.getBalance() + "元");
		if(account.withdraw(500)){
			System.out.println("取款500元后，余额为" + account.getBalance() + "元");
		}else{
			System.out.println("余额不足500元，取款失败，余额保持不变");
		}
	}
}
			