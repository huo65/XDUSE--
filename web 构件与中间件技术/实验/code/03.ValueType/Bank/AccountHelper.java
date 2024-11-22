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
public final class AccountHelper {
  private static org.omg.CORBA.TypeCode _type;
  private static boolean _initializing = false;

  private static org.omg.CORBA.ORB _orb () {
    return org.omg.CORBA.ORB.init();
  }

  public static Bank.Account read (final org.omg.CORBA.portable.InputStream _input) {
    if (!(_input instanceof org.omg.CORBA_2_3.portable.InputStream)) {
      throw new org.omg.CORBA.BAD_PARAM();
    }
    return (Bank.Account)((org.omg.CORBA_2_3.portable.InputStream)_input).read_value(id());
  }

  public static void write (final org.omg.CORBA.portable.OutputStream _output, final Bank.Account _vis_value) {
    if (!(_output instanceof org.omg.CORBA_2_3.portable.OutputStream)) {
      throw new org.omg.CORBA.BAD_PARAM();
    }
    ((org.omg.CORBA_2_3.portable.OutputStream)_output).write_value((java.io.Serializable)_vis_value, id());
  }

  public static void insert (final org.omg.CORBA.Any any, final Bank.Account _vis_value) {
    any.insert_Value((java.io.Serializable)_vis_value, Bank.AccountHelper.type());
  }

  public static Bank.Account extract (final org.omg.CORBA.Any any) {
    if(!any.type().equivalent(type())) {
      throw new org.omg.CORBA.BAD_TYPECODE();
    }
    Bank.Account _vis_value;
    final java.io.Serializable _val = any.extract_Value();
    if (_val instanceof Bank.Account) {
      _vis_value = (Bank.Account)_val;
    }
    else {
      throw new org.omg.CORBA.BAD_PARAM("error extracting Bank.Account from any");
    }
    return _vis_value;
  }

  public static org.omg.CORBA.TypeCode type () {
    if (_type == null) {
      synchronized(org.omg.CORBA.TypeCode.class) {
        if (_type == null) {
          if (_initializing) {
            return _orb().create_recursive_tc(id());
          }
          _initializing = true;
          final org.omg.CORBA.ValueMember[] _members = new org.omg.CORBA.ValueMember[1];
          _members[0] = new org.omg.CORBA.ValueMember("balance",
                                                      "IDL:Bank/Account/balance:1.0",
                                                      id(), "1.0",
                                                      _orb().get_primitive_tc(org.omg.CORBA.TCKind.tk_float),
                                                      null,
                                                      org.omg.CORBA.PRIVATE_MEMBER.value);
          _type = _orb().create_value_tc(id(), "Account", org.omg.CORBA.VM_NONE.value, null, _members);
          _initializing = false;
        }
      }
    }
    return _type;
  }

  public static java.lang.String id () {
    return "IDL:Bank/Account:1.0";
  }

  private static Bank.AccountValueFactory getValueFactory(org.omg.CORBA.ORB orb) {
    if (!(orb instanceof org.omg.CORBA_2_3.ORB)) {
      throw new org.omg.CORBA.BAD_PARAM();
    }

    org.omg.CORBA.portable.ValueFactory factory = ((org.omg.CORBA_2_3.ORB)orb).lookup_value_factory(id());
    if (factory instanceof Bank.AccountValueFactory) {
      return (Bank.AccountValueFactory)factory;
    }

    throw new org.omg.CORBA.BAD_PARAM();
  }

  public static Bank.Account open (org.omg.CORBA.ORB orb, float init) {
    return getValueFactory(orb).open(init);
  }
}
