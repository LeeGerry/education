package edu.auburn.utils;

import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

/**
 * JDBC工具类
 */
public class JDBCUtil {
	private static String url = null;
	private static String user = null;
	private static String pwd = null;
	private static String driverClass = null;
	private static String dbConfigPath = "/db.properties";
	static{
		try {
			Properties props = new Properties();
			InputStream in = JDBCUtil.class.getResourceAsStream(dbConfigPath);
			props.load(in);
			url = props.getProperty("url");
			user = props.getProperty("user");
			pwd = props.getProperty("password");
			driverClass = props.getProperty("driverClass");
			Class.forName(driverClass);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	public static Connection getConnection(){
		try {
			return (Connection) DriverManager.getConnection(url, user, pwd);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void close(Connection conn, Statement stmt){
		if(stmt != null){
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
