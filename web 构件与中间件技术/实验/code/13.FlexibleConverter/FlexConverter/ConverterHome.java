package FlexConverter;

import java.io.Serializable;
import java.rmi.RemoteException;
import javax.ejb.*;
import javax.naming.*;

public interface ConverterHome extends EJBHome
{
	public Converter create() throws RemoteException, CreateException;
}
	