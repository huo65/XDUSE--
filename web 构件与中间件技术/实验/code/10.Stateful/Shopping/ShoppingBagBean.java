package Shopping;

import javax.ejb.*;
import java.util.*;
public class ShoppingBagBean implements SessionBean
{
	SessionContext Context;
	String curCustomer;
	Hashtable curCommodities;
	public void addCom(Commodity comm){
		Commodity nowComm = (Commodity)curCommodities.get(comm.sCommodityID);
		if(nowComm == null){
			curCommodities.put(comm.sCommodityID, comm);
		}else{
			nowComm.iCount += comm.iCount;
		}
	}
	public void removeComm(Commodity comm) throws NoSuchCommodityException{
		Commodity nowComm = (Commodity)curCommodities.get(comm.sCommodityID);
		if(nowComm == null)
			throw new NoSuchCommodityException("No such commodity with id: " + comm.sCommodityID);
		nowComm.iCount -= comm.iCount;
		if(nowComm.iCount <= 0)
			curCommodities.remove(comm.sCommodityID);
	}
	public Commodity find(String commID) throws NoSuchCommodityException{
		Commodity nowComm = (Commodity)curCommodities.get(commID);
		if(nowComm == null)
			throw new NoSuchCommodityException("No such commodity with id: " + commID);
		return nowComm;
	}
	public void clearBag(){
		curCommodities.clear();
	}
	public void commit() throws BagEmptyException{
		if(curCommodities.isEmpty())
			throw new BagEmptyException("Bag is empty");
		System.out.println("Customer name is: " + curCustomer);
		System.out.println("\tID\t\tName\tSpec\tPrice\tCount");
		Enumeration enums = curCommodities.elements();
		while(enums.hasMoreElements()){
			Commodity comm = (Commodity)enums.nextElement();
			System.out.println("\t" + comm.sCommodityID + "\t" + comm.sName + "\t" + comm.sSpec + "\t" + comm.fPrice + "\t" + comm.iCount);
		}
		curCommodities.clear();
	}

	public void ejbCreate(String customerName){
		curCustomer = customerName;
		curCommodities = new Hashtable();
		System.out.println("ShoppingBagBean ejbCreate with name:" + customerName);
	}
	public void ejbRemove(){
		curCommodities.clear();
		System.out.println("ShoppingBagBean ejbRemove");
	}
	public void ejbPassivate(){
		System.out.println(curCustomer + "ShoppingBagBean ejbPassivate");
	}
	public void ejbActivate(){
		System.out.println("ShoppingBagBean ejbActivate");
	}
	public void setSessionContext(SessionContext Context)
	{
		//System.out.println("ShoppingBagBean setSessionContext");
		this.Context = Context;
	}
}