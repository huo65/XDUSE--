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
public class AccountDefaultFactory implements Bank.AccountValueFactory {
  public java.io.Serializable read_value (org.omg.CORBA_2_3.portable.InputStream is) {
    // INSTANTIATE IMPLEMENTATION CLASS ON THE LINE BELOW:
    java.io.Serializable val = new AccountImpl(0);
        // 通过read_value从InputStream中读取对象状态
        val = ((org.omg.CORBA_2_3.portable.InputStream) is).
			read_value(val);
      return val;
  }

  /**
   * <pre>
   *   valuetype Account  {
    ...
  };
   * </pre>
   */
  public Bank.Account open (float init) {
    // IMPLEMENT METHOD AND REMOVE THE LINE BELOW:
    throw new org.omg.CORBA.NO_IMPLEMENT();
  }

}
