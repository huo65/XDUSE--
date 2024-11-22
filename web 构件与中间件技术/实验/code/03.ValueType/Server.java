import Bank.*;
import org.omg.PortableServer.*;
public class Server
{
  public static void main(String[] args)
  {
  try {
    org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args, null);
    POA rootPOA = POAHelper.narrow(
      orb.resolve_initial_references("RootPOA"));
    org.omg.CORBA.Policy[] policies = {
      rootPOA.create_lifespan_policy(
		LifespanPolicyValue.PERSISTENT)
    };
    POA myPOA = rootPOA.create_POA("BankPOA",
      rootPOA.the_POAManager(), policies);
    AccountManagerImpl managerServant = 
		new AccountManagerImpl();
    myPOA.activate_object_with_id(
        "BankManager".getBytes(), managerServant);
    rootPOA.the_POAManager().activate();
    System.out.println("帐户管理员BankManager已就绪 ...\n");
    orb.run();
  } catch(Exception exc) {
    exc.printStackTrace();
  }
 }
}
