package FlexConverter;

import javax.ejb.*;
import javax.naming.*;

public class ConverterBean implements SessionBean
{
	SessionContext ctx;
	public double dollarToYen(double dollars){
		Context initCtx, myEnv;
		Double dollarsToYen = null;
		try{
		
			initCtx = new InitialContext();
			//myEnv = (Context)initCtx.lookup("java:comp/env");
			//dollarsToYen = (Double)myEnv.lookup("dollarsToYen");
			dollarsToYen = (Double)initCtx.lookup("java:comp/env/dollarsToYen");
			//java.util.Properties prop = ctx.getEnvironment();
		
			//dollarsToYen = (Double)ctx.lookup("dollarsToYen");
		}catch(NamingException ne){
			ne.printStackTrace();
		}
		return dollarsToYen.doubleValue() * dollars;
	}
	
	public double yenToEuro(double yen){
		Context initCtx, myEnv;
		Double yenToEuro = null;
		try{
			initCtx = new InitialContext();
			myEnv = (Context)initCtx.lookup("java:comp/env");
			yenToEuro = (Double)myEnv.lookup("yenToEuro");
		}catch(NamingException ne){
			ne.printStackTrace();
		}
		return yenToEuro.doubleValue() * yen;
	}
		
	public ConverterBean() {}
	public void ejbCreate() {}
	public void ejbRemove() {}
	public void ejbPassivate() {}
	public void ejbActivate() {}
	public void setSessionContext(SessionContext Context) {
		ctx = Context;
	}
}