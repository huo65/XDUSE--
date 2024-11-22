//The code is used to create the client application used by 
//the HelloApp application
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
			
			Hashtable env = new Hashtable();
			env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.cosnaming.CNCtxFactory");
			env.put(Context.PROVIDER_URL, "iiop://127.0.0.1:1050");			
			InitialContext Init = new InitialContext(env);					
	
			HelloHome Home = (HelloHome)Init.lookup("HelloJ");
			Hello HelloObj = Home.create();
			String RetVal = HelloObj.sayHello("Future Software Engineers!!！");
			
			System.out.println("Returned>>: " + RetVal);
			//容器不允许客户端删除EJB的实例
			//Home.remove(HelloObj);
			HelloObj.remove();
		}catch(java.rmi.RemoteException exception){
			System.out.println("Remote exception occurred: " + exception);
		}catch(javax.ejb.CreateException exception){
			System.out.println("Create exception occurred: " + exception);
		}catch(javax.ejb.RemoveException exception){
			System.out.println("Remove exception occurred: " + exception);
		}catch(javax.naming.NamingException exception){
			System.out.println("Naming exception occurred: " + exception);
		}/*catch(UserException1 exception){
			System.out.println("user exception occurred: " + exception);
		}catch(UserException2 exception){
			System.out.println("user exception occurred: " + exception);
		}*/
	}
}