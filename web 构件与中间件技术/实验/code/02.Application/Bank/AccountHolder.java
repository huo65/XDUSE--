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
public final class AccountHolder implements org.omg.CORBA.portable.Streamable {
  public Bank.Account value;

  public AccountHolder () {
  }

  public AccountHolder (final Bank.Account _vis_value) {
    this.value = _vis_value;
  }

  public void _read (final org.omg.CORBA.portable.InputStream input) {
    value = Bank.AccountHelper.read(input);
  }

  public void _write (final org.omg.CORBA.portable.OutputStream output) {
    Bank.AccountHelper.write(output, value);
  }

  public org.omg.CORBA.TypeCode _type () {
    return Bank.AccountHelper.type();
  }
}
