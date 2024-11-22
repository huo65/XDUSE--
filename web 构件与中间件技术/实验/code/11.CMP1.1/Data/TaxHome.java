//home interface used by TaxBean
package Data;
import java.util.Collection;
import java.rmi.RemoteException;
import javax.ejb.*;
public interface TaxHome extends EJBHome
{
	public Tax create(String stateCode, float taxRate)
		throws RemoteException, CreateException;
	public Tax findByPrimaryKey(String primaryKey) 
		throws RemoteException, FinderException;
	public Collection findInRange(float lowerLimit, float upperLimit)
		throws RemoteException, FinderException;
}