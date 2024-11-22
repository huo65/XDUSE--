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
public class _AccountManagerStub extends com.inprise.vbroker.CORBA.portable.ObjectImpl implements Bank.AccountManager {
  final public static java.lang.Class _opsClass = Bank.AccountManagerOperations.class;

  public java.lang.String[] _ids () {
    return __ids;
  }

  private static java.lang.String[] __ids = {
    "IDL:Bank/AccountManager:1.0"
  };

  /**
   * <pre>
   *   Bank.Account open (in string name);
   * </pre>
   */
  public Bank.Account open (java.lang.String name) {

    while (true) {
      if (!_is_local()) {
        org.omg.CORBA.portable.OutputStream _output = null;
        org.omg.CORBA.portable.InputStream  _input  = null;
        Bank.Account _result;
        try {
          _output = this._request("open", true);
          _output.write_string((java.lang.String)name);
          _input = this._invoke(_output);
          _result = Bank.AccountHelper.read(_input);
          return _result;
        }
        catch (org.omg.CORBA.portable.ApplicationException _exception) {
          final org.omg.CORBA.portable.InputStream in = _exception.getInputStream();
          java.lang.String _exception_id = _exception.getId();
          throw new org.omg.CORBA.UNKNOWN("Unexpected User Exception: " + _exception_id);
        }
        catch (org.omg.CORBA.portable.RemarshalException _exception) {
          continue;
        }
        finally {
          this._releaseReply(_input);
        }
      } else {
        final org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("open", _opsClass);
        if (_so == null) {
          continue;
        }
        final Bank.AccountManagerOperations _self = (Bank.AccountManagerOperations)_so.servant;
        try {
          return _self.open(name);
        }
        finally {
          _servant_postinvoke(_so);
        }
      }
    }
  }

}
