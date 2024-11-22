import examples.local_objects.*;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;

public class EBClient{
	public static void main(String[] args){
		try{
			InitialContext initial = new InitialContext();
			OrderHome orderHome = (OrderHome)PortableRemoteObject.narrow(initial.lookup("OrderBean"), OrderHome.class);
			Order order;
			AddressValueObject shipAddress;
			AddressValueObject obj1 = new AddressValueObject(2, "北雷2","郭杜2", "长安2", "710126");
			//System.out.println("new address OK");
		
			order = orderHome.create(2, "张三2", obj1);
			System.out.println(order.getCustomerName());
			//shipAddress = order.getShipAddressView();
			//System.out.println(shipAddress.getStreet());
			//System.out.println(shipAddress.getCity());
			//System.out.println(shipAddress.getState());
			//System.out.println(shipAddress.getZip());
			order = orderHome.findByPrimaryKey(new Long(2));
			shipAddress = order.getShipAddressView();
			System.out.println(shipAddress.getStreet());
			System.out.println(shipAddress.getCity());
			System.out.println(shipAddress.getState());
			System.out.println(shipAddress.getZip());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
