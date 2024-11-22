package bankWeb;

import banking.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.rmi.RemoteException;
import javax.ejb.*;
import javax.naming.*;
import javax.rmi.PortableRemoteObject;

public class BankServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<HTML>");
		out.println("<HEAD><TITLE>BankServlet</TITLE></HEAD>");
		out.println("<BODY>");
//		out.println("Hello " + "<br>");
		try{
//			out.println("looking up AccountManager...<br>");
			Context initial = new InitialContext();
//			out.println("new InitialContext OK<br>");
			AccountManagerHome accountManagerHome = (AccountManagerHome)PortableRemoteObject.narrow(
				initial.lookup("java:comp/env/ejb/AccountManager"), AccountManagerHome.class);
			//out.println("look up AccountManager OK<br>");
			AccountManager accountManager = accountManagerHome.create();
//			out.println("create AccountManager 'accountManager' successfully<br>");
//  		Account account1 = accountManager.createAccount("name1", 800.00f);
//			out.println("create Account 'name1' successfully<br>");
			out.println("Account name1's balance is: " + accountManager.getBalance("name1") + "<br>");
//			accountManager.removeAccount("name1");
			Account account = accountManager.createAccount("newAccount", 1000.00f);
			out.println("createAccount 'newAccount' successfully<br>");
			accountManager.removeAccount("newAccount");
			out.println("removeAccount successfully<br>");
		}catch(RemoteException re){
			out.println("RemoteException : " + re.getMessage());
		}catch(NoAccountCreatedException nace){
			out.println("NoAccountCreatedException : " + nace.getMessage());
		}catch(CreateException ce){
			out.println("CreateException : " + ce.getMessage());
		}catch(Exception e){
			out.println("Get Exception: " + e.getMessage());
		}
		out.println("</BODY>");
		out.println("</HTML>");
		out.close();
	}
}
