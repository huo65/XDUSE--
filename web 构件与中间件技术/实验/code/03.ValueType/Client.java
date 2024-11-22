import Bank.*;
public class Client
{
  public static void main(String[] args)
  {
    org.omg.CORBA.ORB orb=org.omg.CORBA.ORB.init(args, null);
    // 利用POA全称与对象标识"BankManager"查找帐户管理员
    Bank.AccountManager manager = 
	Bank.AccountManagerHelper.bind(
      		orb, "/BankPOA", "BankManager".getBytes());
    String name = args.length > 0 ? args[0] : "SHLSong";
    // 请求帐户管理员找出一个指定名字的帐户，无此帐户则新开一个
    Bank.Account account = manager.open(name);
    System.out.println(name + "的帐户余额为" + 
		account.getBalance() + "元");
    account.deposit(200);
    System.out.println("存款200元后，本地余额为"+ 
		account.getBalance() + "元");
    account = manager.open(name);
    System.out.println("服务端" + name + "的帐户余额为" + 
		account.getBalance() + "元");
  }
}
