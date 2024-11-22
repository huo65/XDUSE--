package Telephone;

public class CallManager
	extends java.rmi.server.UnicastRemoteObject
	implements CallManagerInterface
{
	protected Database.DatabaseAccess database;
	
	//缺省构造方法，必须抛出RemoteException
	public CallManager()
		throws java.rmi.RemoteException
	{
		database = new Database.DatabaseAccess();
	}
	
	//为了防止多个用户并发的调用数据库查询操作，定义为同步方法。
	public synchronized Database.DatabaseTableModel getCallHistory(String subscriber)
		throws java.rmi.RemoteException
	{
		String sql = "";
		Database.DatabaseTableModel table = null;
		System.out.println("Respond to client request: " + subscriber);
		try{
			sql = "QueryCallHistoryWithSubscriber('" + subscriber + "')";
			java.sql.ResultSet rs = database.callQuery(sql);
			table = new Database.DatabaseTableModel(rs);
			rs.close();
		}catch(java.sql.SQLException exc){
			System.out.println(exc.getMessage());
			System.exit(1);
		}
		return table;
	}
}