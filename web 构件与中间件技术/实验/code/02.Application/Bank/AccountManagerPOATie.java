
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
public class AccountManagerPOATie extends AccountManagerPOA {
  private Bank.AccountManagerOperations _delegate;
  private org.omg.PortableServer.POA _poa;

  public AccountManagerPOATie (final Bank.AccountManagerOperations _delegate) {
    this._delegate = _delegate;
  }

  public AccountManagerPOATie (final Bank.AccountManagerOperations _delegate, 
                              final org.omg.PortableServer.POA _poa) {
    this._delegate = _delegate;
    this._poa = _poa;
  }

  public Bank.AccountManagerOperations _delegate () {
    return this._delegate;
  }

  public void _delegate (final Bank.AccountManagerOperations delegate) {
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
   *   Bank.Account open (in string name);
   * </pre>
   */
  public Bank.Account open (java.lang.String name) {
    return this._delegate.open(name);
  }

}
