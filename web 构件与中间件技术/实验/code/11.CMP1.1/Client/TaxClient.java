//Client class
import Data.*;
import java.util.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;

public class TaxClient{
	public static void main(String[] args){
		try{
			Context initial = new InitialContext();
			Object objRef = initial.lookup("TaxBean");
			TaxHome home = (TaxHome) PortableRemoteObject.narrow(objRef, TaxHome.class);
			Tax tax = null;
			
			tax = home.create("IL", 5.00f);
			tax = home.create("CA", 6.25f);
			tax = home.create("FL", 8.50f);
			tax = home.create("CO", 6.75f);
			
			tax = home.findByPrimaryKey("CA");
			
			System.out.println("CA tax rate: " + tax.getTaxRate());
			System.out.println("Changing tax rate for CA state");
			tax.setTaxRate(8.25f);
			
			System.out.println("New CA tax rate: " + tax.getTaxRate());
			
			Collection taxArray = home.findInRange(5.0f, 7.0f);
			
			Iterator it = taxArray.iterator();
			while(it.hasNext()){
				Object objRef2 = it.next();
				tax = (Tax)PortableRemoteObject.narrow(objRef2, Tax.class);
				System.out.println("Tax Rate in " + tax.getPrimaryKey() + ": " + tax.getTaxRate());
				tax.remove();
			}
		}catch(Exception ex){
			System.err.println("Caught an exception.");
			ex.printStackTrace();
		}
	}
}
			