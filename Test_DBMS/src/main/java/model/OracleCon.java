package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleCon  {
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			
		}catch(ClassNotFoundException e)
				{
			e.printStackTrace();
				}
	}
	public static Connection createConnection() throws SQLException{
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/Newark_IT_DBMS", "root", "myserver");
		
	}
}
