import java.sql.*;

public class CreateSampleData
{
	final static String JDBC_DRIVER = "COM.cloudscape.core.RmiJdbcDriver";
	final static String DATABASE_NAME = "jdbc:cloudscape:rmi:CloudscapeDB";
	protected static Connection conn;
	
	public static void main(String args[])
	{
		//加载JDBC驱动
		try{
			System.out.println("Loading jdbc driver...");
			Class.forName(JDBC_DRIVER);
			System.out.println("Load jdbc driver OK");
		}catch(ClassNotFoundException exc){
			System.out.println(exc.getMessage());
			System.exit(1);
		}
		//创建数据库
		try{
			conn = DriverManager.getConnection(DATABASE_NAME);
			Statement stmt = conn.createStatement();
			System.out.println("createStatement");
      try{
			    stmt.execute("DROP TABLE accounts");
			    System.out.println("table droped");
      }catch(Exception exc) {
          System.out.println(exc.getMessage());
      }
			stmt.execute("CREATE TABLE accounts (accountname CHAR(20), balance INTEGER)");
			System.out.println("table created");
			stmt.execute("INSERT INTO accounts VALUES('name1', 2000)");
			System.out.println("insert name1");
			stmt.execute("INSERT INTO accounts VALUES('name2', 3000)");
			System.out.println("insert name2");
			conn.close();
		}catch(Exception exc){
			System.out.println(exc.getMessage());
			System.exit(1);
		}

	}
}