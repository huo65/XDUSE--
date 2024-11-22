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
public final class AccountManagerHolder implements org.omg.CORBA.portable.Streamable {
  public Bank.AccountManager value;

  public AccountManagerHolder () {
  }

  public AccountManagerHolder (final Bank.AccountManager _vis_value) {
    this.value = _vis_value;
  }

  public void _read (final org.omg.CORBA.portable.InputStream input) {
    value = Bank.AccountManagerHelper.read(input);
  }

  public void _write (final org.omg.CORBA.portable.OutputStream output) {
    Bank.AccountManagerHelper.write(output, value);
  }

  public org.omg.CORBA.TypeCode _type () {
    return Bank.AccountManagerHelper.type();
  }
}
