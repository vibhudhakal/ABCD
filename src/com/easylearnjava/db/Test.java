package com.easylearnjava.db;

	import java.sql.ResultSet;
	import java.sql.SQLException;
import java.sql.CallableStatement;
//import com.easylearnjava.exception.ServiceException;
	//import com.easylearnjava.util.DBConnection;
	import java.sql.Connection;
	import java.sql.PreparedStatement;

	public class Test {		
		public static void main(String[] args) throws ClassNotFoundException, SQLException {
			
			Test ld = new Test();
			String pwd=ld.getPassword("vibhu");
			System.out.println("The Password for vibhu is:"+pwd);
			int id=ld.getId("sandesh", "thapa1");
			System.out.println("The ID for sandesh is:"+id);
			ld.getUserPassCallStmt("sandesh");
			ld.insertUserRecord(3, "prabin", "bhandari");
			
		}
		
		public String getPassword(String uname) throws ClassNotFoundException, SQLException {

			Connection con;
			PreparedStatement stmt;
			DBConnection dbConn = new DBConnection();
			String pwdFromdB = null;
			con=dbConn.giveMeABridge();
			stmt=con.prepareStatement("SELECT * from USER WHERE USER_NAME=?");
			stmt.setString(1, uname);
			ResultSet rs=stmt.executeQuery();
			while(rs.next()){
				int id= rs.getInt("USER_ID");
				String un=rs.getString("USER_NAME");
				pwdFromdB=rs.getString("USER_PASSWORD");

			System.out.println(id +" "+un+ " "+pwdFromdB);
				
			}
			return pwdFromdB;
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
		
			public int getId(String uname, String upass) throws ClassNotFoundException, SQLException {

				Connection con;
				PreparedStatement stmt;
				DBConnection dbConn = new DBConnection();
				int userID = 0;
				con=dbConn.giveMeABridge();
				stmt=con.prepareStatement("SELECT * from USER WHERE USER_NAME=? and USER_PASSWORD=? ");
				stmt.setString(1, uname);
				stmt.setString(2, upass);
				ResultSet rs=stmt.executeQuery();
				while(rs.next()){
				userID= rs.getInt("USER_ID");
					String un=rs.getString("USER_NAME");
					String ps=rs.getString("USER_PASSWORD");

				System.out.println(userID +" "+un+ " "+ps);
					
				}
				return userID;
			}
			
				
				
				public void  getUserPassCallStmt(String uname) throws ClassNotFoundException, SQLException {

					Connection con;
					CallableStatement stmt;
					DBConnection dbConn = new DBConnection();
					String pwd=null;
					con=dbConn.giveMeABridge();
					String sql="{call getUserPassword(?,?)}";
					stmt=con.prepareCall(sql);
					stmt.setString(1, uname);
					stmt.registerOutParameter(2, java.sql.Types.VARCHAR);
					stmt.executeQuery();
					pwd = stmt.getString(2);
					System.out.println("Password for the " + uname+ " is:"+pwd);
					
		}
				
				
				
				public void insertUserRecord(int id,String uname, String password) throws ClassNotFoundException, SQLException {

					Connection con;
					PreparedStatement stmt;
					DBConnection dbConn = new DBConnection();
					con=dbConn.giveMeABridge();
					stmt=con.prepareStatement("INSERT INTO USER VALUES(?,?,?)");
					stmt.setInt(1, id);
					stmt.setString(2, uname);
					stmt.setString(3, password);
			        stmt.execute();
		}
				
	}

