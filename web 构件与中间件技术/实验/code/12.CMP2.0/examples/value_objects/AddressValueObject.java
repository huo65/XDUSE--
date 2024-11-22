//EJB 2.0
package examples.value_objects;

public class AddressValueObject implements java.io.Serializable{
	private int addressID;
	private String street;
	private String city;
	private String state;
	private String zip;
	
	public AddressValueObject() {}
	public AddressValueObject(int addressID, String street, String city, String state, String zip){
		this.addressID = addressID;
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}
	public int getAddressID() {
		return addressID;
	}
	public void setAddressID(int addressID){
		this.addressID = addressID;
	}		

	public String getStreet() {
		return street;
	}
	public void setStreet(String street){
		this.street = street;
	}		
	
	public String getCity() {
		return city;
	}
	public void setCity(String city){
		this.city = city;
	}		

	public String getState() {
		return state;
	}
	public void setState(String state){
		this.state = state;
	}		

	public String getZip() {
		return zip;
	}
	public void setZip(String zip){
		this.zip = zip;
	}		
}