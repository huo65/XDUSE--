import org.omg.PortableServer.*;
import org.omg.PortableServer.ServantLocatorPackage.CookieHolder;
import Bank.*;

public class AccountManagerLocator
	extends ServantLocatorPOA
{
	public Servant preinvoke(byte[] oid, POA adapter,
		java.lang.String operation, CookieHolder the_cookie)
		throws ForwardRequest
	{
		Servant servant;
		
		System.out.println("preinvoke with ID = " + new String(oid));
		if((new String(oid)).equalsIgnoreCase("Zhang3"))
			servant = (Servant) new AccountManagerImpl_1();
		else
			servant = (Servant) new AccountManagerImpl_2();
		return servant;
	}
	
	public void postinvoke(byte[] oid, POA adapter, java.lang.String operation,
		java.lang.Object the_cookie, Servant the_servant)
	{
		System.out.println("postinvoke with ID = " + new String(oid));
	}
}