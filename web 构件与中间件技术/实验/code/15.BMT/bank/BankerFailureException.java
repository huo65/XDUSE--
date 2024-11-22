package bank;
public class BankerFailureException extends Exception{
	public BankerFailureException() {}
	public BankerFailureException(String msg){
		super(msg);
	}
}