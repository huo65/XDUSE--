//The code is used to create the client application used by 
//the CurTimeApp application
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
			Context initial = new InitialContext(env);
			Object objref = initial.lookup("CurTime");
			CurTimeHome Home = (CurTimeHome)PortableRemoteObject.narrow(objref, CurTimeHome.class);
			EJBMetaData md = Home.getEJBMetaData();
			CurTime CurTimeObj = Home.create();
			String RetVal;
			for(int i=0; i<5; i++){
				RetVal = CurTimeObj.getCurTime();
				System.out.println("Returned<"+i+">:"+ RetVal);
			}			
			RetVal = CurTimeObj.getCurTime();
			System.out.println("Returned: " + RetVal);
			//容器不允许客户端删除EJB的实例
			//Home.remove(CurTimeObj);
			CurTimeObj.remove();
			//Thread.currentThread().sleep(30000);
	 	}catch(java.rmi.RemoteException exception){
			System.out.println("Remote exception occurred: " + exception);
		}catch(javax.ejb.CreateException exception){
			System.out.println("Create exception occurred: " + exception);
		}catch(javax.ejb.RemoveException exception){
			System.out.println("Remove exception occurred: " + exception);
		}catch(javax.naming.NamingException exception){
			System.out.println("Naming exception occurred: " + exception);
		}
	}
}