
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
public class AccountPOATie extends AccountPOA {
  private Bank.AccountOperations _delegate;
  private org.omg.PortableServer.POA _poa;

  public AccountPOATie (final Bank.AccountOperations _delegate) {
    this._delegate = _delegate;
  }

  public AccountPOATie (final Bank.AccountOperations _delegate, 
                              final org.omg.PortableServer.POA _poa) {
    this._delegate = _delegate;
    this._poa = _poa;
  }

  public Bank.AccountOperations _delegate () {
    return this._delegate;
  }

  public void _delegate (final Bank.AccountOperations delegate) {
    this._delegate = delegate;
  }

  public org.omg.PortableServer.POA _default_POA () {
    if (_poa != null) {
      return _poa;
    } 
    else {
      return super._default_POA();
    }
  }

  /**
   * <pre>
   *   void deposit (in float amount);
   * </pre>
   */
  public void deposit (float amount) {
    this._delegate.deposit(amount);
  }

  /**
   * <pre>
   *   boolean withdraw (in float amount);
   * </pre>
   */
  public boolean withdraw (float amount) {
    return this._delegate.withdraw(amount);
  }

  /**
   * <pre>
   *   float getBalance ();
   * </pre>
   */
  public float getBalance () {
    return this._delegate.getBalance();
  }

}
