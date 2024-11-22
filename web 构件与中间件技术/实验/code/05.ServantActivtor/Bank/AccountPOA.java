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
public abstract class AccountPOA extends org.omg.PortableServer.Servant implements 
  org.omg.CORBA.portable.InvokeHandler, Bank.AccountOperations {

  public Bank.Account _this () {
   return Bank.AccountHelper.narrow(super._this_object());
  }

  public Bank.Account _this (org.omg.CORBA.ORB orb) {
    return Bank.AccountHelper.narrow(super._this_object(orb));
  }

  public java.lang.String[] _all_interfaces (final org.omg.PortableServer.POA poa, final byte[] objectId) {
    return __ids;
  }

  private static java.lang.String[] __ids = {
    "IDL:Bank/Account:1.0"
  };

  private static java.util.Dictionary _methods = new java.util.Hashtable();

  static {
    _methods.put("deposit", new int[] { 0, 0 });
    _methods.put("withdraw", new int[] { 0, 1 });
    _methods.put("getBalance", new int[] { 0, 2 });
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
        return Bank.AccountPOA._invoke(this, method[1], _input, handler);
      }
    }
    throw new org.omg.CORBA.BAD_OPERATION();
  }

  public static org.omg.CORBA.portable.OutputStream _invoke (Bank.AccountOperations _self,
                                                             int _method_id,
                                                             org.omg.CORBA.portable.InputStream _input,
                                                             org.omg.CORBA.portable.ResponseHandler _handler) {
    org.omg.CORBA.portable.OutputStream _output = null;
    {
      switch (_method_id) {
      case 0: {
        float amount;
        amount = _input.read_float();
        _self.deposit(amount);
        _output = _handler.createReply();
        return _output;
      }
      case 1: {
        float amount;
        amount = _input.read_float();
        boolean _result = _self.withdraw(amount);
        _output = _handler.createReply();
        _output.write_boolean((boolean)_result);
        return _output;
      }
      case 2: {
        float _result = _self.getBalance();
        _output = _handler.createReply();
        _output.write_float((float)_result);
        return _output;
      }
      }
      throw new org.omg.CORBA.BAD_OPERATION();
    }
  }
}
