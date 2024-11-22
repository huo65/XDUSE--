package banking;
public class NoAccountCreatedException extends Exception{
	public NoAccountCreatedException(String msg){
		super(msg);
	}
}