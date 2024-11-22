package bank;
public class PushFailureException extends Exception{
	public PushFailureException() {}
	public PushFailureException(String msg){
		super(msg);
	}
}