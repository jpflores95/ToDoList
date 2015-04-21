package todochallenge.db;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;

public class DBPool {
	private static DBPool singleton;
	private BasicDataSource ds;
	private String username = "root";
	private String password = "p@ssword";
	private String url = "jdbc:mysql://localhost:3306/tododb";
	
	private DBPool(){
		ds = new BasicDataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUsername(username);
		ds.setPassword(password);
		ds.setUrl(url);
	}
	
	public static DBPool getInstance(){
		if(singleton==null)
			singleton = new DBPool();
		return singleton;
	}
	
	public Connection getConnection(){
		try {
			return ds.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;
	}
}
