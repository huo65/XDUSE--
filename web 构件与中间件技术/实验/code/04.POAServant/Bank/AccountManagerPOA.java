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
public abstract class AccountManagerPOA extends org.omg.PortableServer.Servant implements 
  org.omg.CORBA.portable.InvokeHandler, Bank.AccountManagerOperations {

  public Bank.AccountManager _this () {
   return Bank.AccountManagerHelper.narrow(super._this_object());
  }

  public Bank.AccountManager _this (org.omg.CORBA.ORB orb) {
    return Bank.AccountManagerHelper.narrow(super._this_object(orb));
  }

  public java.lang.String[] _all_interfaces (final org.omg.PortableServer.POA poa, final byte[] objectId) {
    return __ids;
  }

  private static java.lang.String[] __ids = {
    "IDL:Bank/AccountManager:1.0"
  };

  private static java.util.Dictionary _methods = new java.util.Hashtable();

  static {
    _methods.put("open", new int[] { 0, 0 });
  }

  public org.omg.CORBA.portable.OutputStream _invoke (java.lang.String opName,
                                                      org.omg.CORBA.portable.InputStream _input,
                                                      org.omg.CORBA.portable.ResponseHandler handler) {
    int[] method = (int[]) _methods.get(opName);
    if (method == null) {
      throw new org.omg.CORBA.BAD_OPERATION();
    }
    switch (method[0]) {
      case 0: {
        return Bank.AccountManagerPOA._invoke(this, method[1], _input, handler);
      }
    }
    throw new org.omg.CORBA.BAD_OPERATION();
  }

  public static org.omg.CORBA.portable.OutputStream _invoke (Bank.AccountManagerOperations _self,
                                                             int _method_id,
                                                             org.omg.CORBA.portable.InputStream _input,
                                                             org.omg.CORBA.portable.ResponseHandler _handler) {
    org.omg.CORBA.portable.OutputStream _output = null;
    {
      switch (_method_id) {
      case 0: {
        java.lang.String name;
        name = _input.read_string();
        Bank.Account _result = _self.open(name);
        _output = _handler.createReply();
        Bank.AccountHelper.write(_output, _result);
        return _output;
      }
      }
      throw new org.omg.CORBA.BAD_OPERATION();
    }
  }
}
