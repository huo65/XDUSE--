
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
public final class AccountManagerHelper {
  public static Bank.AccountManager narrow (final org.omg.CORBA.Object obj) {
    return narrow(obj, false);
  }

  public static Bank.AccountManager unchecked_narrow (org.omg.CORBA.Object obj) {
    return narrow(obj, true);
  }

  private static Bank.AccountManager narrow (final org.omg.CORBA.Object obj, final boolean is_a) {
    if (obj == null) {
      return null;
    }
    if (obj instanceof Bank.AccountManager) {
      return (Bank.AccountManager)obj;
    }
    if (is_a || obj._is_a(id())) {
      final org.omg.CORBA.portable.ObjectImpl _obj = (org.omg.CORBA.portable.ObjectImpl)obj;
      Bank._AccountManagerStub result = new Bank._AccountManagerStub();
      final org.omg.CORBA.portable.Delegate _delegate = _obj._get_delegate();
      result._set_delegate(_delegate);
      return result;
    }
    throw new org.omg.CORBA.BAD_PARAM();
  }

  public static Bank.AccountManager bind (org.omg.CORBA.ORB orb) {
    return bind(orb, null, null, null);
  }

  public static Bank.AccountManager bind (org.omg.CORBA.ORB orb, java.lang.String name) {
    return bind(orb, name, null, null);
  }

  public static Bank.AccountManager bind (org.omg.CORBA.ORB orb,
                                          java.lang.String name,
                                          java.lang.String host,
                                          com.inprise.vbroker.CORBA.BindOptions _options) {
    if (!(orb instanceof com.inprise.vbroker.CORBA.ORB)) {
      throw new org.omg.CORBA.BAD_PARAM();
    }
    return narrow(((com.inprise.vbroker.CORBA.ORB)orb).bind(id(), name, host, _options), true);
  }

  public static Bank.AccountManager bind (org.omg.CORBA.ORB orb, java.lang.String fullPoaName, byte[] oid) {
    return bind(orb, fullPoaName, oid, null, null);
  }

  public static Bank.AccountManager bind (org.omg.CORBA.ORB orb,
                                          java.lang.String fullPoaName,
                                          byte[] oid, java.lang.String host,
                                          com.inprise.vbroker.CORBA.BindOptions _options) {
    if (!(orb instanceof com.inprise.vbroker.CORBA.ORB)) {
      throw new org.omg.CORBA.BAD_PARAM();
    }
    return narrow(((com.inprise.vbroker.CORBA.ORB)orb).bind(fullPoaName, oid, host, _options), true);
  }

  public java.lang.Object read_Object (final org.omg.CORBA.portable.InputStream istream) {
    return read(istream);
  }

  public void write_Object (final org.omg.CORBA.portable.OutputStream ostream, final java.lang.Object obj) {
    if (!(obj instanceof Bank.AccountManager)) {
      throw new org.omg.CORBA.BAD_PARAM();
    }
    write(ostream, (Bank.AccountManager)obj);
  }

  public java.lang.String get_id () {
    return id();
  }

  public org.omg.CORBA.TypeCode get_type () {
    return type();
  }

  private static org.omg.CORBA.TypeCode _type;
  private static boolean _initializing;

  private static org.omg.CORBA.ORB _orb () {
    return org.omg.CORBA.ORB.init();
  }

  public static Bank.AccountManager read (final org.omg.CORBA.portable.InputStream _input) {
    return narrow(_input.read_Object(Bank._AccountManagerStub.class), true);
  }

  public static void write (final org.omg.CORBA.portable.OutputStream _output, final Bank.AccountManager _vis_value) {
    if (!(_output instanceof org.omg.CORBA_2_3.portable.OutputStream)) {
      throw new org.omg.CORBA.BAD_PARAM();
    }
    if (_vis_value != null && !(_vis_value instanceof org.omg.CORBA.portable.ObjectImpl)) {
      throw new org.omg.CORBA.BAD_PARAM();
    }
    _output.write_Object((org.omg.CORBA.Object)_vis_value);
  }

  public static void insert (final org.omg.CORBA.Any any, final Bank.AccountManager _vis_value) {
    any.insert_Object((org.omg.CORBA.Object)_vis_value, Bank.AccountManagerHelper.type());
  }

  public static Bank.AccountManager extract (final org.omg.CORBA.Any any) {
    Bank.AccountManager _vis_value;
    final org.omg.CORBA.Object _obj = any.extract_Object();
    _vis_value = Bank.AccountManagerHelper.narrow(_obj);
    return _vis_value;
  }

  public static org.omg.CORBA.TypeCode type () {
    if (_type == null) {
      synchronized (org.omg.CORBA.TypeCode.class) {
        if (_type == null) {
          _type = _orb().create_interface_tc(id(), "AccountManager");
        }
      }
    }
    return _type;
  }

  public static java.lang.String id () {
    return "IDL:Bank/AccountManager:1.0";
  }
}
