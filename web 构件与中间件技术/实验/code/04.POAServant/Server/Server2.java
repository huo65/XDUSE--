import org.omg.PortableServer.*;
public class Server
{
	public static void main(String[] args)
	{
		try{
			org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args, null);
       			POA rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA")); 
       			org.omg.CORBA.Policy[] policies = {
        			rootPOA.create_lifespan_policy(LifespanPolicyValue.PERSISTENT),
        			rootPOA.create_id_uniqueness_policy(
				IdUniquenessPolicyValue.MULTIPLE_ID),
        			rootPOA.create_request_processing_policy(
          			RequestProcessingPolicyValue.USE_DEFAULT_SERVANT)
       			};
       			POA newPOA = rootPOA.create_POA(
         			"DefaultServantBankPOA", rootPOA.the_POAManager(), policies);
       			AccountManagerImpl managerServant = new AccountManagerImpl();
                AccountManagerImpl managerServant1= new AccountManagerImpl();//
       			newPOA.set_servant(managerServant);
       			rootPOA.the_POAManager().activate();
       			newPOA.create_reference_with_id(
                			"Zhang3".getBytes(), "IDL:Bank/AccountManager:1.0");
       			newPOA.activate_object_with_id("Li4".getBytes(), managerServant1);
 				//newPOA.create_reference_with_id(
                //			"Li4".getBytes(), "IDL:Bank/AccountManager:1.0");
       			orb.run();

		}catch(Exception exc){
			exc.printStackTrace();
		}
	}
}