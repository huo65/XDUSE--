package examples.local_objects;

import javax.ejb.*;

public interface AddressLocalHome extends EJBLocalHome{
	public AddressLocal findByPrimaryKey(Integer addressID)
			throws FinderException;
	public AddressLocal create(int addressID, String street, String city, String state, String zip)
			throws CreateException;
}