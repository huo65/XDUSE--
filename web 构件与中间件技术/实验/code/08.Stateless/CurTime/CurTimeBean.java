//The code is used to create the enterprise bean class
//used by the CurTimeApp application
import javax.ejb.*;
import java.sql.*;
public class CurTimeBean implements SessionBean
{
	SessionContext Context;
	//无状态的session bean只能包含无参数的create
	public void ejbCreate(){
		//ii = i;
		System.out.println("\n******::CurTimeBean ejbCreate");
	}// throws CreateException, UserException2{}
	public String getCurTime()
	{
		Timestamp ts=new Timestamp(System.currentTimeMillis()); 
		return ts.toString();
	}
	public void ejbRemove(){
		System.out.println("CurTimeBean ejbRemove");
	}
	public void ejbPassivate(){}
	public void ejbActivate(){}
	public void setSessionContext(SessionContext Context)
	{
		this.Context = Context;
	}
}