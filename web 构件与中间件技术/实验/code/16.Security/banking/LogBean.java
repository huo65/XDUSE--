package banking;

import javax.ejb.*;
import javax.naming.*;

public abstract class LogBean implements EntityBean{
	private EntityContext context;
	public LogBean() {}
	//CMP fields
	public abstract String getLogMessage();
	public abstract void setLogMessage(String logMessage);
	
	//business method
	public String ejbCreate(String msg)
			throws CreateException{
		setLogMessage(msg);
		return null;
	}
	
	public void log(String msg){
		setLogMessage(msg);
	}
	
	public void ejbPostCreate(String msg) {}
	public void ejbActivate() {}
	public void ejbLoad() {}
	public void ejbPassivate() {}
	public void ejbRemove() {}
	public void ejbStore() {}
	public void setEntityContext(EntityContext ctx) {
		context = ctx;	
	}
	public void unsetEntityContext() {
		context = null;
	}
}