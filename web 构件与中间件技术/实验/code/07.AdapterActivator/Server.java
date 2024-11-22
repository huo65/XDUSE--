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
			AdapterActivator bankAA = new BankAdapterActivator()._this(orb);
			rootPOA.the_activator(bankAA);
			//创建持久POA的策略
			org.omg.CORBA.Policy[] policies = {
				rootPOA.create_lifespan_policy(LifespanPolicyValue.PERSISTENT),
				rootPOA.create_id_uniqueness_policy(IdUniquenessPolicyValue.MULTIPLE_ID),
				rootPOA.create_request_processing_policy(
					RequestProcessingPolicyValue.USE_DEFAULT_SERVANT)
			};
			//用新定义的策略创建myPOA
			POA grandPOA = rootPOA.create_POA("GrandPOA", rootPOA.the_POAManager(), policies);
			POA parentPOA = grandPOA.create_POA("ParentPOA", rootPOA.the_POAManager(), policies);
			POA childPOA = parentPOA.create_POA("ChildPOA", rootPOA.the_POAManager(), policies);
			org.omg.CORBA.Object ior = childPOA.create_reference_with_id(
				"BankManager".getBytes(), "IDL:Bank/AccountManager:1.0");
			String iorString = orb.object_to_string(ior);
			//将IOR保存到文件中供客户程序使用
			try{
				java.io.PrintWriter writer = new java.io.PrintWriter(
					new java.io.FileWriter("BankIOR.dat"));
				writer.println(iorString);
				writer.close();
			}catch(java.io.IOException exc){
				System.out.println("将IOR写入文件时出错：" + exc.getMessage());
				return;
			}
			grandPOA.destroy(true,true);
			//激活POA管理器
			rootPOA.the_POAManager().activate();
			//等待处理客户程序的请求
			System.out.println("帐户管理员BankManager已就绪...\n");
			orb.run();
		}catch(Exception exc){
			exc.printStackTrace();
		}
	}
}