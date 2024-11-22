//The code is used to create the remote interface of an enterprise bean
//used by the CurTimeApp application
import javax.ejb.*;
import java.rmi.*;
public interface CurTime extends EJBObject
{
	String getCurTime()  throws RemoteException;
}