public class ServerApplication
{
	final static String JDBC_DRIVER = "sun.jdbc.odbc.JdbcOdbcDriver";
	
	public static void main(String args[])
	{
		//为RMI设置安全管理器
		System.setSecurityManager(new java.rmi.RMISecurityManager());
		//加载JDBC驱动
		try{
			Class.forName(JDBC_DRIVER);
		}catch(ClassNotFoundException exc){
			System.out.println(exc.getMessage());
			System.exit(1);
		}
		//创建并注册伺服对象
		try{
			//创建伺服对象
			Telephone.CallManager callManager = new Telephone.CallManager();
			//用名字"CallManagerServant001"注册伺服对象
			java.rmi.Naming.rebind("//localhost/CallManagerServant001", callManager);
		}catch(java.rmi.RemoteException exc){
			System.out.println(exc.getMessage());
			System.exit(1);
		}catch(java.net.MalformedURLException exc){
			System.out.println(exc.getMessage());
			System.exit(1);
		}
		System.out.println("Call manager in the server is ready ...");
	}
}