package FlexConverter;
import javax.ejb.*;
import java.rmi.*;
public interface Converter extends EJBObject
{
	public double dollarToYen(double dollars) throws RemoteException;
	public double yenToEuro(double yen) throws RemoteException; 
}