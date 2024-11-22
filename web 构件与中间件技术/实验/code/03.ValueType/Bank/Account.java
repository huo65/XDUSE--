package Bank;

/**
 * <ul>
 * <li> <b>IDL Source</b>    "Bank.idl"
 * <li> <b>IDL Name</b>      ::Bank::Account
 * <li> <b>Repository Id</b> IDL:Bank/Account:1.0
 * </ul>
 * <b>IDL definition:</b>
 * <pre>
 * valuetype Account  {
  ...
};
 * </pre>
 */
public abstract class Account implements org.omg.CORBA.portable.StreamableValue  {
  /**
   * <pre>
   *   private float balance;
   * </pre>
   */
  protected float balance;
  /**
   * <pre>
   *   void deposit (in float amount);
   * </pre>
   */
  abstract public void deposit (float amount);

  /**
   * <pre>
   *   boolean withdraw (in float amount);
   * </pre>
   */
  abstract public boolean withdraw (float amount);

  /**
   * <pre>
   *   float getBalance ();
   * </pre>
   */
  abstract public float getBalance ();

  public java.lang.String toString() {
    final java.lang.StringBuffer _ret = new java.lang.StringBuffer("valuetype Bank.Account {");

    _ret.append("\n");
    _ret.append("float balance=");
    _ret.append(balance);
    _ret.append("\n");
    _ret.append("}");
    return _ret.toString();
  }

  public boolean equals (java.lang.Object o) {
    if (this == o) return true;
    if (o == null) return false;

    if (o instanceof Bank.Account) {
      final Bank.Account obj = (Bank.Account)o;
      boolean res = true;
      do {
        res = this.balance == obj.balance;
      } while (false);
      return res;
    }
    else {
      return false;
    }
  }

  public org.omg.CORBA.TypeCode _type () {
    return Bank.AccountHelper.type();
  }

  public void _read (final org.omg.CORBA.portable.InputStream _input) {
    balance = _input.read_float();
  }

  public void _write (final org.omg.CORBA.portable.OutputStream _output) {
    _output.write_float((float)balance);
  }

  private static java.lang.String[] _truncatable_ids = {
    "IDL:Bank/Account:1.0"
  };

  public java.lang.String[] _truncatable_ids () {
    return _truncatable_ids;
  }
}
