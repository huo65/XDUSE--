package examples.local_objects;

import javax.ejb.*;

public abstract class AddressBean implements EntityBean{
	public abstract int getAddressID();
	public abstract void setAddressID(int addressID);

	public abstract String getStreet();
	public abstract void setStreet(String street);

	public abstract String getCity();
	public abstract void setCity(String city);

	public abstract String getState();
	public abstract void setState(String state);

	public abstract String getZip();
	public abstract void setZip(String zip);
	
	public Integer ejbCreate(int addressID, String street, String city, String state, String zip)
			throws CreateException{
		System.out.println("entry AddressBean ejbCreate");
		setAddressID(addressID);
		setStreet(street);
		setCity(city);
		setState(state);
		setZip(zip);
		return null;
	}
	
	public void ejbPostCreate(int addressID, String street, String city, String state, String zip)
			throws CreateException {}
	public void ejbActivate() {}
	public void ejbLoad() {}
	public void ejbPassivate() {}
	public void ejbRemove() {}
	public void ejbStore() {}
	public void setEntityContext(EntityContext ctx) {}
	public void unsetEntityContext() {}
}	
