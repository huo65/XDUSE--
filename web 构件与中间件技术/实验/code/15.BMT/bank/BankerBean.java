package bank;

import javax.ejb.*;
import javax.naming.*;
import java.sql.*;
import javax.sql.*;
import java.util.Random;
import javax.transaction.*;

public class BankerBean implements SessionBean
{
	DataSource ds;
	Connection conn;
	SessionContext sessionCtx = null;

	public void deposit(String accountName, int amount)throws BankerFailureException{
		try{
			conn = ds.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery("SELECT * FROM accounts WHERE accountname = '" + accountName + "'");
			if(res.next()){
				int newBalance;
				newBalance = res.getInt("balance") + amount;
				stmt.execute("UPDATE accounts SET balance = " + newBalance);
			}
			else{
				throw new BankerFailureException("invalid accountName");
			}
			conn.close();
		}catch(Exception e){
				throw new BankerFailureException("invalid accountName");
		}
	}
	public void withdraw(String accountName, int amount) throws BankerFailureException{
		System.out.println("Entry withdraw");
		try{
			System.out.println("Getting transaction object...");
			UserTransaction userTrx = sessionCtx.getUserTransaction();
			userTrx.begin();
			System.out.println("user transaction begin...");
		}catch(Exception exc){
			throw new BankerFailureException("transaction not begin");
		}

		try{
			conn = ds.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery("SELECT * FROM accounts WHERE accountname = '" + accountName + "'");
			//从账户上减去相应的金额
			int newBalance;
			if(res.next()){
				if(amount > res.getInt("balance")){
					throw new BankerFailureException("no enough balance");
				}
				newBalance = res.getInt("balance") - amount;
				stmt.execute("UPDATE accounts SET balance = " + newBalance);
			}
			else{
				throw new BankerFailureException("invalid accountName");
			}
			conn.close();
			System.out.println(accountName + "'s balance changed to " + newBalance);
			System.out.println("pushing cash...");
			//操纵取款机为用户吐出现金
			pushCash(amount);
			System.out.println("withdraw finished successfully");
			try{
				UserTransaction userTrx = sessionCtx.getUserTransaction();
				userTrx.commit();
				System.out.println("user transaction committed");
			}catch(Exception exc){
				throw new BankerFailureException("transaction commit failed");
			}
		}catch(SQLException e){
			System.out.println("SQLException caught, try rollback");
			try{
				UserTransaction userTrx = sessionCtx.getUserTransaction();
				userTrx.rollback();
				System.out.println("user transaction rollbacked");
				throw new BankerFailureException("operation failed");
			}catch(BankerFailureException exc){
				throw new BankerFailureException(exc.getMessage());
			}catch(Exception exc){
				throw new BankerFailureException("transaction rollback failed");
			}
		}
		catch(Exception e){
			System.out.println("Exception caught, try rollback");
			try{
				UserTransaction userTrx = sessionCtx.getUserTransaction();
				userTrx.rollback();
				System.out.println("user transaction rollbacked");
				throw new BankerFailureException("operation failed");
			}catch(BankerFailureException exc){
				throw new BankerFailureException(exc.getMessage());
			}catch(Exception exc){
				throw new BankerFailureException("transaction rollback failed");
			}
		}
	}
	private void pushCash(int amount){
		Random rand = new Random();
		int i =Math.abs(rand.nextInt()); 
		if(i > 1000000000){
			System.out.println("pushCash failed(" + i +")");
			throw new RuntimeException();
		}
	}

	public int getBalance(String accountName) throws BankerFailureException{
		try{
			conn = ds.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery("SELECT * FROM accounts WHERE accountname = '" + accountName + "'");
			//从账户上减去相应的金额
			int curBalance;
			if(res.next()){
				curBalance = res.getInt("balance");
				conn.close();
				return curBalance;
			}
			else{
				throw new BankerFailureException("invalid accountName");
			}
		}catch(Exception e){
			throw new BankerFailureException("operation failed");
		}
	}		
	
	public void ejbCreate() throws CreateException{
		try{
//			System.out.println("Entry ejbCreate");
			InitialContext initialCtx = new InitialContext();
//			System.out.println("new InitialContext");
			ds = (DataSource)initialCtx.lookup("java:comp/env/jdbc/BankDB");
//			System.out.println("lookup");
		}catch(NamingException ex){
			throw new CreateException("lookup datasource failed");
		}catch(Exception e){
			throw new CreateException("operation failed");
		}

	}
	public void ejbRemove() {}
	public void ejbPassivate() {}
	public void ejbActivate() {}
	public void setSessionContext(SessionContext Context) {
		sessionCtx = Context;
	}
}