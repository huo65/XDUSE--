import Bank.*;
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
				rootPOA.create_request_processing_policy(
					RequestProcessingPolicyValue.USE_SERVANT_MANAGER)
			};
			//用新定义的策略创建myPOA
			POA myPOA = rootPOA.create_POA("ServantActivatorPOA", rootPOA.the_POAManager(), policies);
			ServantActivator sa = new AccountManagerActivator()._this(orb);
			myPOA.set_servant_manager(sa);
			//激活POA管理器
			rootPOA.the_POAManager().activate();
			myPOA.create_reference_with_id(
				"Zhang3".getBytes(), "IDL:Bank/AccountManager:1.0");
			myPOA.create_reference_with_id(
				"Li4".getBytes(), "IDL:Bank/AccountManager:1.0");
			//等待处理客户程序的请求
			System.out.println("帐户管理员Zhang3和Li4已就绪...\n");
			orb.run();
		}catch(Exception exc){
			exc.printStackTrace();
		}
	}
}