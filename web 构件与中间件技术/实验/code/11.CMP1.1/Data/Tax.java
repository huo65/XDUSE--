//remote interface used by TaxBean
package Data;

import java.rmi.RemoteException;
import javax.ejb.EJBObject;

public interface Tax extends EJBObject
{
	public void setTaxRate(float taxRate) throws RemoteException;
	public float getTaxRate() throws RemoteException;
}