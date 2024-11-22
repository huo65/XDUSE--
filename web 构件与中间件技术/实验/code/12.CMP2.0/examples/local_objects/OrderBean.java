//EJB 2.0
package examples.local_objects;

import javax.ejb.CreateException;
import javax.ejb.EntityBean;
import javax.ejb.EntityContext;
import javax.naming.InitialContext;

public abstract class OrderBean implements EntityBean{
	//CMP fields
	public abstract long getOrderID();
	public abstract void setOrderID(long orderID);
	
	public abstract String getCustomerName();
	public abstract void setCustomerName(String customerName);
	
	//CMR fields
	public abstract AddressLocal getShipAddress();
	public abstract void setShipAddress(AddressLocal shipAddress);
	
	public AddressValueObject getShipAddressView(){
		AddressLocal shipAddress = getShipAddress();
		return new AddressValueObject(shipAddress.getAddressID(),
										shipAddress.getStreet(),
										shipAddress.getCity(),
										shipAddress.getState(),
										shipAddress.getZip());
	}
	
	public Long ejbCreate(long orderID, String customerName, AddressValueObject shipAddress)
			throws CreateException{
		System.out.println("entry ejbCreate");
		setOrderID(orderID);
		setCustomerName(customerName);
		return null;
	}
	private AddressLocal createShipAddress(int addressId, String street, String city, String state, String zip){
		try{
			InitialContext initial = new InitialContext();
			AddressLocalHome home = (AddressLocalHome)initial.lookup("java:comp/env/ejb/AddressEJB");
			return home.create(addressId, street, city, state, zip);
		}catch(Exception e){
			throw new javax.ejb.EJBException(e);
		}
	}		
//	public void ejbPostCreate(long orderID, String customerName)
	public void ejbPostCreate(long orderID, String customerName, AddressValueObject shipAddressView)
			throws CreateException{
		AddressLocal shipAddress = createShipAddress(
			shipAddressView.getAddressID(), shipAddressView.getStreet(),
			shipAddressView.getCity(), shipAddressView.getState(),
			shipAddressView.getZip());
		setShipAddress(shipAddress);
	}
	public void ejbActivate() {}
	public void ejbLoad(){
		System.out.println("OrderBean ejbLoad");
		if(getShipAddress() != null)
			System.out.println("shipAddress not null");
		else
			System.out.println("shipAddress null");
	}
	public void ejbPassivate() {}
	public void ejbRemove() {}
	public void ejbStore() {}
	public void setEntityContext(EntityContext ctx) {}
	public void unsetEntityContext() {}
}