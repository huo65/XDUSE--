import org.omg.PortableServer.*;
import Bank.*;

public class BankAdapterActivator
	extends AdapterActivatorPOA
{
	public boolean unknown_adapter(POA parent, String name)
	{
		System.out.println("auto-generated POA " + parent.the_name() + "/" + name);
		org.omg.CORBA.Policy[] policies = {
			parent.create_lifespan_policy(LifespanPolicyValue.PERSISTENT),
			parent.create_id_uniqueness_policy(IdUniquenessPolicyValue.MULTIPLE_ID),
			parent.create_request_processing_policy(
				RequestProcessingPolicyValue.USE_DEFAULT_SERVANT)
		};
		POA child = null;
		try{
			child = parent.create_POA(name, parent.the_POAManager(), policies);
		}catch(Exception exc){
			exc.printStackTrace();
			return false;
		}
		child.the_activator(parent.the_activator());
		if(name.equals("ChildPOA")){
			System.out.println("正在激活ChildPOA的缺省伺服对象...");
			try{
				child.set_servant(new AccountManagerImpl());
				child.create_reference_with_id(
					"BankManager".getBytes(), "IDL:Bank/AccountManager:1.0");
			}catch(Exception exc){
				exc.printStackTrace();
			}
		}
		return true;
	}
}