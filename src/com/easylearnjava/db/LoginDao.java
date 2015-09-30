package com.easylearnjava.db;

import java.sql.ResultSet;
import java.sql.SQLException;
//import com.easylearnjava.exception.ServiceException;
//import com.easylearnjava.util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class LoginDao {
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		LoginDao ld = new LoginDao();
		ld.getPassword();
	}
	
	public void getPassword() throws ClassNotFoundException, SQLException {

		Connection con;
		PreparedStatement stmt;
		DBConnection dbConn = new DBConnection();
		String passwordFromDB = null;
		con=dbConn.giveMeABridge();
		stmt=con.prepareStatement("SELECT * from USER WHERE USER_NAME=?");
		ResultSet rs=stmt.executeQuery();
		while(rs.next()){
			int id= rs.getInt("USER_ID");
			String un=rs.getString("USER_NAME");
			String pwd=rs.getString("USER_PASSWORD");

		System.out.println(id +" "+un+ " "+pwd );
			
		}
		
/*
		try {
			con = dbConn.getDBConnection();

			String sql = "SELECT user_password FROM  USER where user_name = ? ";
			stmt = (PreparedStatement) con.prepareStatement(sql);
			stmt.setString(1, userName);

			ResultSet res = stmt.executeQuery();
			while (res.next()) {
				passwordFromDB = res.getString("USER_PASSWORD");
			}
			dbConn.closeConnection();
		} catch (Exception ex) {
			ex.printStackTrace(); 
			throw new ServiceException();
		}
		return passwordFromDB;*/
	}

}
