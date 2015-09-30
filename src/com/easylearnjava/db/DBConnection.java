package com.easylearnjava.db;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.sql.Connection;

public class DBConnection {
	

    Connection conn=null;
	String driver;
	String url;
	String userName;
	String password;
	
	
	public Connection giveMeABridge() throws ClassNotFoundException, SQLException{
		loadDbProperties();
		Class.forName(driver);
		conn= DriverManager.getConnection(url, userName, password);
		return conn;
	}

	public void loadDbProperties() {
		// Step 8: Resource bundle looks for a file named dbParameters.properties in the classpath
		ResourceBundle labels = ResourceBundle.getBundle("dbParameters");
		driver = labels.getString("DB_DRIVER");
		url = labels.getString("DB_URL");
		userName = labels.getString("DB_USER");
		password = labels.getString("DB_PASSWORD");
	}
	
	public void closeConnection() {
		try {
			conn.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Exception when trying to close data base connetion");
		}
	}


	
}
