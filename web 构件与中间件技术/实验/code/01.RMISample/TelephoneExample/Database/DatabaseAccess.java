package Database;

import java.sql.*;

public class DatabaseAccess
{
	protected final String DATABASE_NAME = "jdbc:odbc:Telephone";
	
	protected Connection connection;	//为数据库建立的连接
	protected Statement statement;		//将执行的SQL语句
	protected CallableStatement callable;	//将执行的SQL存储过程
	
	public DatabaseAccess()
	{
		try{
			//建立与指定数据库的连接
			connection = DriverManager.getConnection(DATABASE_NAME);
			//如果连接成功则检测是否有警告信息
			SQLWarning warn = connection.getWarnings();
			while(warn != null){
				System.out.println(warn.getMessage());
				warn = warn.getNextWarning();
			}
			//创建一个用于执行SQL的语句
			statement = connection.createStatement();
			callable = null;
		}catch(SQLException exc){
			System.out.println(exc.getMessage());
			System.exit(1);
		}
	}
	
	public synchronized void finalize()
	{
		try{
			connection.close();
		}catch(SQLException exc){
			System.out.println(exc.getMessage());
			System.exit(1);
		}
	}
	
	public synchronized ResultSet callQuery(String procedure)
		throws SQLException
	{
		callable = connection.prepareCall("{call " + procedure + "}");
		ResultSet rs = callable.executeQuery();
		return rs;
	}
}