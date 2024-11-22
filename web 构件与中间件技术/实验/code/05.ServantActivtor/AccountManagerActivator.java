import org.omg.PortableServer.*;
import Bank.*;

public class AccountManagerActivator
	extends ServantActivatorPOA
{
	public Servant incarnate(byte[] oid, POA adapter)
		throws ForwardRequest
	{
		Servant servant;
		
		System.out.println("incarnate with ID = " + new String(oid));
		if((new String(oid)).equalsIgnoreCase("Zhang3"))
			servant = (Servant) new AccountManagerImpl_1();
		else
			servant = (Servant) new AccountManagerImpl_2();
		new DeactivateThread(oid, adapter).start();
		return servant;
	}
	
	public void etherealize(byte[] oid, POA adapter, Servant serv,
		boolean cleanup_in_progress, boolean remaining_activations)
	{
		System.out.println("etherealize with ID = " + new String(oid));
		System.gc();
	}
	
	class DeactivateThread
		extends Thread
	{
		byte[] _oid;
		POA _adapter;
		
		public DeactivateThread(byte[] oid, POA adapter)
		{
			_oid = oid;
			_adapter = adapter;
		}
		
		public void run()
		{
			try{
				Thread.currentThread().sleep(5000);
				System.out.println("dactivate with ID = " + new String(_oid));
				_adapter.deactivate_object(_oid);
			}catch(Exception exc){
				exc.printStackTrace();
			}
		}
	}
}