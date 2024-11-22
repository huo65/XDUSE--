//EJB 2.0
package examples.value_objects;

import javax.ejb.CreateException;
import javax.ejb.EntityBean;
import javax.ejb.EntityContext;

public abstract class OrderBean implements EntityBean{
	//CMP fields
	public abstract long getOrderID();
	public abstract void setOrderID(long orderID);
	
	public abstract String getCustomerName();
	public abstract void setCustomerName(String customerName);
	
	public abstract AddressValueObject getShipAddress();
	public abstract void setShipAddress(AddressValueObject shipAddress);
	
	public Long ejbCreate(long orderID, String customerName, AddressValueObject shipAddress)
			throws CreateException{
		System.out.println("entry ejbCreate");
		setOrderID(orderID);
		setCustomerName(customerName);
		setShipAddress(shipAddress);
		return null;
	}
	public void ejbPostCreate(long orderID, String customerName, AddressValueObject shipAddress)
//	public void ejbPostCreate(long orderID, String customerName)
			throws CreateException{}
	public void ejbActivate() {}
	public void ejbLoad() {}
	public void ejbPassivate() {}
	public void ejbRemove() {}
	public void ejbStore() {}
	public void setEntityContext(EntityContext ctx) {}
	public void unsetEntityContext() {}
}