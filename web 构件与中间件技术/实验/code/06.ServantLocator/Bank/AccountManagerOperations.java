package Bank;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "Bank.idl"
 * <li> <b>IDL Name</b>      ::Bank::AccountManager
 * <li> <b>Repository Id</b> IDL:Bank/AccountManager:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface AccountManager {
  ...
};
 * </pre>
 */
public interface AccountManagerOperations {
  /**
   * <pre>
   *   Bank.Account open (in string name);
   * </pre>
   */
  public Bank.Account open (java.lang.String name);

}
