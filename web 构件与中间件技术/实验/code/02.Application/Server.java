import org.omg.PortableServer.*;

public class Server
{
	public static void main(String[] args)
	{
		try{
			//初始化ORB
			org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args, null);
			//获取根POA的引用
			POA rootPOA = POAHelper.narrow(
				orb.resolve_initial_references("RootPOA"));
			//创建持久POA的策略
			org.omg.CORBA.Policy[] policies = {
				rootPOA.create_lifespan_policy(LifespanPolicyValue.PERSISTENT),
				//rootPOA.create_id_assignment_policy(IdAssignmentPolicyValue.USER_ID)
			};
			//用新定义的策略创建myPOA
			POA myPOA = rootPOA.create_POA("BankPOA", rootPOA.the_POAManager(), policies);
			//创建伺服对象
			AccountManagerImpl managerServant = new AccountManagerImpl();
			//在myPOA上用标识"BankManager"激活伺服对象
			myPOA.activate_object_with_id("BankManager".getBytes(), managerServant);
			//激活POA管理器
			rootPOA.the_POAManager().activate();
			//等待处理客户程序的请求
			System.out.println("账户管理员BankManager已就绪...\n");
			orb.run();
		}catch(Exception exc){
			exc.printStackTrace();
		}
	}
}