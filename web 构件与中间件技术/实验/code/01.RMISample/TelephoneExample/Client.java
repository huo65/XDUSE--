public class Client
{
	// 实现main 方法，根据参数调用分布式对象的getCallHistory 完成通话记录的查询
	public static void main(String[] args)
	{
		String name = args.length > 0? args[0]:"songshengli";
        //System.out.println(name);
		Database.DatabaseTableModel result = null;
		try {
			// 从Applet 取主机名
			String host = "//localhost/";
			// 远程对象的标识必须与服务程序注册时使用的对象标识完全相同
			String objectId = "CallManagerServant001";
			// 根据主机名与对象标识解析远程对象
			Telephone.CallManagerInterface callManager = (Telephone.CallManagerInterface)
			java.rmi.Naming.lookup(host + objectId);
			// 调用远程对象方法查询用户的通话记录
			result = callManager.getCallHistory(name);
			result.printData();
		} catch(Exception exc) {
		System.out.println(exc.getMessage());
		System.exit(1);
		}
	}
}