import java.rmi.*;
import javax.naming.*;
import java.util.*;
import javax.rmi.*;
import javax.ejb.*;
import Shopping.*;

public class StatefulClient
{
	public static void main(String[] args)
	{
		try{
			
			//Hashtable env = new Hashtable();
			//env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.cosnaming.CNCtxFactory");
			//env.put(Context.PROVIDER_URL, "iiop://000.000.000.0000:1050");			
			//InitialContext Init = new InitialContext(env);	
			
			InitialContext Init = new InitialContext();			
			
			ShoppingBagHome Home = (ShoppingBagHome)Init.lookup("ShoppingBag");
			
			ShoppingBag ShoppingBagObj;
			
			ShoppingBagObj = Home.create("Mr. Zhang");
			
			Commodity comm = new Commodity();
			comm.sCommodityID = "10000001"; comm.sName = "Phone"; comm.sSpec = "T29"; comm.fPrice = 1000; comm.iCount = 2;
			ShoppingBagObj.addCom(comm);
			comm.sCommodityID = "10000002"; comm.sName = "Camera"; comm.sSpec = "D300"; comm.fPrice = 1500; comm.iCount = 1;
			ShoppingBagObj.addCom(comm);
			
			try{
				comm = ShoppingBagObj.find("10000001");
				System.out.println("we have commodities with id 10000001, name is " + comm.sName);
			}catch(NoSuchCommodityException e){
				System.out.println(e.getMessage());
			}
			try{
				//ShoppingBagObj.removeComm(comm);
				comm = ShoppingBagObj.find("10000001");
				System.out.println("we have commodities with id 10000001, name is " + comm.sName + ", number is "+comm.iCount);
			}catch(NoSuchCommodityException e){
				System.out.println(e.getMessage());
			}
			try{
				ShoppingBagObj.commit();
			}catch(BagEmptyException e){
				System.out.println(e.getMessage());
			}
			//ShoppingBagObj.remove();
			//Home.remove(Home.getHomeHandle());
		}catch(java.rmi.RemoteException exception){
			System.out.println("Remote exception occurred: " + exception);
		}catch(javax.ejb.CreateException exception){
			System.out.println("Create exception occurred: " + exception);
		//}catch(javax.ejb.RemoveException exception){
			//System.out.println("Remove exception occurred: " + exception);
		}catch(javax.naming.NamingException exception){
			System.out.println("Naming exception occurred: " + exception);
		}
	}
}