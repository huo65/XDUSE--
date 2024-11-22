package examples.local_objects;

import javax.ejb.EJBLocalObject;

public interface AddressLocal extends EJBLocalObject{
	public int getAddressID();
	
	public String getStreet();
	public void setStreet(String street);
	
	public String getCity();
	public void setCity(String city);
	
	public String getState();
	public void setState(String state);
	
	public String getZip();
	public void setZip(String zip);
}