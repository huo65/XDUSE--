package banking;
public class AccountAccessDeniedException extends Exception{
	public AccountAccessDeniedException(String msg){
		super(msg);
	}
}