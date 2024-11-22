//The code is used to create the client application used by 
//the HelloApp application
import banking.*;
import java.rmi.*;
import javax.naming.*;
import java.util.*;
import javax.rmi.*;
import javax.ejb.*;

public class HClient
{
	public static void main(String[] args)
	{
		try{
			InitialContext Init = new InitialContext();
      AccountHome accountHome = (AccountHome)Init.lookup("Account");
			Account account = accountHome.create("name1", 800.00f);
			System.out.println("create Account 'name1' successfully");
		}catch(java.rmi.RemoteException exception){
			System.out.println("Remote exception occurred: " + exception);
		}catch(javax.ejb.CreateException exception){
			System.out.println("Create exception occurred: " + exception);
		}catch(javax.naming.NamingException exception){
			System.out.println("Naming exception occurred: " + exception);
		}catch(Exception exp) {
			System.out.println("Exception occurred: " + exp);
		}
	}
}