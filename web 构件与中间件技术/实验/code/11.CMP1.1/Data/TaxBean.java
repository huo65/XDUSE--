//entity bean class(CMP)
package Data;

import java.util.*;
import javax.ejb.*;

public class TaxBean implements EntityBean
{
	public String stateCode;
	public float taxRate;
	
	public void setTaxRate(float taxRate){
		this.taxRate = taxRate;
	}
	public float getTaxRate(){
		return this.taxRate;
	}
	public String ejbCreate(String stateCode, float taxRate)
			throws CreateException{
		System.out.println("TaxBean ejbCreate with stateCode=" + stateCode + ",taxRate=" + taxRate);
		if(stateCode == null){
			throw new CreateException("The State Code is required.");
		}
		this.stateCode = stateCode;
		this.taxRate = taxRate;
		
		return null;
	}
	public void ejbPostCreate(String stateCode, float taxRate){		
		System.out.println("TaxBean ejbPostCreate whith stateCode=" + stateCode + ",taxRate=" + taxRate);
	}
	public void ejbLoad(){
		System.out.println("TaxBean ejbLoad whith stateCode=" + stateCode + ",taxRate=" + taxRate);
		if(stateCode != null)
			stateCode.trim();
	}
	public void ejbStore() {
		System.out.println("TaxBean ejbStore whith stateCode=" + stateCode + ",taxRate=" + taxRate);
	}
	public void ejbRemove() {
		System.out.println("TaxBean ejbRemove");
	}
	public void unsetEntityContext() {}
	public void setEntityContext(EntityContext context) {}
	public void ejbActivate() {}
	public void ejbPassivate() {}
}