
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
public interface AccountValueFactory extends org.omg.CORBA.portable.ValueFactory {
    /**
     * <pre>
     *     valuetype Account  {
      ...
    };
     * </pre>
     */
    public Bank.Account open (float init);

}
