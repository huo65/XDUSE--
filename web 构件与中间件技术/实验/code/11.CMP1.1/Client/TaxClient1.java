//Client class
import Data.*;
import java.util.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;

public class TaxClient1{
	public static void main(String[] args){
		try{
			Context initial = new InitialContext();
			Object objRef = initial.lookup("TaxBean");
			TaxHome home = (TaxHome) PortableRemoteObject.narrow(objRef, TaxHome.class);
			Tax tax = null;

			Collection taxArray = home.findInRange(5.0f, 10.0f);
			
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
			