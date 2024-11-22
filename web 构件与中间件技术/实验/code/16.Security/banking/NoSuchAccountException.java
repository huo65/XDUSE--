package banking;
public class NoSuchAccountException extends Exception{
	public NoSuchAccountException(String msg){
		super(msg);
	}
}