package Bank;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "Bank.idl"
 * <li> <b>IDL Name</b>      ::Bank::Account
 * <li> <b>Repository Id</b> IDL:Bank/Account:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * interface Account {
  ...
};
 * </pre>
 */
public interface AccountOperations {
  /**
   * <pre>
   *   void deposit (in float amount);
   * </pre>
   */
  public void deposit (float amount);

  /**
   * <pre>
   *   boolean withdraw (in float amount);
   * </pre>
   */
  public boolean withdraw (float amount);

  /**
   * <pre>
   *   float getBalance ();
   * </pre>
   */
  public float getBalance ();

}
