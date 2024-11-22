import bank.*;
import java.rmi.RemoteException;
import javax.ejb.*;
import javax.naming.*;
import javax.rmi.PortableRemoteObject;

public class BankerClient{
	public static void main(String[] args){
		try{
			Context initial = new InitialContext();
			BankerHome bankerHome = (BankerHome)PortableRemoteObject.narrow(
				initial.lookup("BankerCMT"), BankerHome.class);
//			System.out.println("look up OK");
//			System.exit(0);
			Banker banker = bankerHome.create();
//			System.out.println("create OK");
//			banker.deposit("name1", 100);
			System.out.println("name1's balance is: " + banker.getBalance("name1"));
			banker.withdraw("name1", 100);
			System.out.println("name1's balance is: " + banker.getBalance("name1"));
		}catch(RemoteException re){
			System.out.println("RemoteException : " + re.getMessage());
		}catch(BankerFailureException be){
			System.out.println("BankerFailureException : " + be.getMessage());
		}catch(CreateException ce){
			System.out.println("CreateException : " + ce.getMessage());
		}catch(Exception e){
			System.out.println("Get Exception: " + e.getMessage());
		}

		try{
			Context initial = new InitialContext();
			BankerHome bankerHome = (BankerHome)PortableRemoteObject.narrow(
				initial.lookup("BankerCMT"), BankerHome.class);
//			System.out.println("look up OK");
//			System.exit(0);
			Banker banker = bankerHome.create();
//			System.out.println("create OK");
			System.out.println("name1's balance is: " + banker.getBalance("name1"));
		}catch(RemoteException re){
			System.out.println("RemoteException : " + re.getMessage());
		}catch(BankerFailureException be){
			System.out.println("BankerFailureException : " + be.getMessage());
		}catch(CreateException ce){
			System.out.println("CreateException : " + ce.getMessage());
		}catch(Exception e){
			System.out.println("Get Exception: " + e.getMessage());
		}
	}
}
