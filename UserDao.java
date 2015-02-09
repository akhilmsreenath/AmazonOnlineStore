package edu.sjsu.cmpe282.dao;

import java.sql.*;
import java.util.Date;

import com.sun.research.ws.wadl.Param;

import edu.sjsu.cmpe282.domain.User;
//import edu.sjsu.cmpe282.domain.ProductInfo;

public class UserDao {
	Connection conn = null;
	Statement stmt = null;

	// Constructure with JDBC connection
	public UserDao()
	{
		try{
			try {
				Class.forName("com.mysql.jdbc.Driver");
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//conn = DriverManager.getConnection("jdbc:mysql://aws.ciyflkhukyi9.us-west-1.rds.amazonaws.com/cloud_services","root","amundsenscott");
			//conn = DriverManager.getConnection("jdbc:mysql://cmpelab1.ctanbdve0zch.us-east-1.rds.amazonaws.com/cloud_services","root","T0ydestroy3r");
			
			//conn = DriverManager.getConnection("jdbc:mysql://localhost/cloud_services","root","root");
			conn = DriverManager.getConnection("jdbc:mysql://aws.ciyflkhukyi9.us-west-1.rds.amazonaws.com:3306/cloud_services","root","amundsenscott");
		}
		catch (SQLException e) {
			e.printStackTrace();

		}
	}


	public User addUser(User user)
	{
		int status = 0;
		int uid =0 ;
		User user1 = new User();
		java.util.Date date = new Date();
		Object param = new java.sql.Timestamp(date.getTime());
		System.out.println("time is " + param + param.toString());
		try {
			if (conn==null) {
				System.out.println("CONNECTION IS NULL");
			}
			stmt = conn.createStatement();
			status = getUserInfo(user);
			//Date d= new Date(0);
			System.out.println("here is request");
			if (status == 2) {
				String query = "INSERT INTO `cloud_services`.`user_table` (`firstName`, `lastName`, `email`, `password`, `admin`, `lastlogin`) " +
					"VALUES ('" + user.getFname() + "', '" + user.getLname() + "', '" + user.getEmail() + "', '" + user.getPassword() + "', '" + user.getAdmin() + "', '"+ param + "');";
				stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
				ResultSet rs = null;
					try{
						rs=stmt.getGeneratedKeys();
						if (rs.next())
						{
							uid = rs.getInt(1);
						}
						else
						{
							throw new RuntimeException("Can't find most recent insert we just entered");
						}
						rs.close();
						rs = null;
					} finally {
		                 if (rs != null) {
		                     try {
		                         rs.close();
		                     } catch (SQLException ex) {} // ignore
		                 }
		                 try {
		                	 stmt.close();
		                 } catch (SQLException ex) {}  // ignore						
					}
					
			} else {
				System.out.println("Email already exists : " + user.getEmail());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("in userdao " + user.isAdmin());
		user1.setFname(user.getFname());
		user1.setLname(user.getLname());
		user1.setEmail(user.getEmail());
		user1.setUserid(uid);
		user1.setAdmin(user.isAdmin());
		user1.setAdmin(user.getAdmin());
		user1.setLast_login(param.toString());
		return user1;
	}

	public int getUserInfo(User user) {
		int status = 0;
		try {
			stmt = conn.createStatement();
			String query = "Select * from cloud_services.user_table where email=\"" + user.getEmail() + "\";";
			ResultSet result = stmt.executeQuery(query);
			if (result.next()) {
				System.out.println("FirstName = " + result.getString(1));
				System.out.println("LastName = " + result.getString(2));
				String password = result.getString(4);
				System.out.println("DB password = " + password);
				if (!user.getPassword().equals(password)) {
					status = 1;
				}
			} else {
				status = 2;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}

	public User checkUser(User user){
		boolean result = false;
		ResultSet rs;
		int userid = 0;
		int admin = 0;
		String last_login = "";
		String origpassword = null;
		User user2 = new User();
		try {
			stmt = conn.createStatement();
			String query = "Select password, UserId, admin, last_login from cloud_services.user_table where email = '"+user.getEmail()+"';";
			rs = stmt.executeQuery(query);
			if (rs.next()) {
				origpassword = rs.getString("password");
				userid = rs.getInt("userid");
				admin = rs.getInt("admin");
				last_login = rs.getString("last_login");
			}
			result = user.getPassword().equals(origpassword);
			System.out.println("em: " + user.getEmail() + " res " + result);
			if (result) {

				java.util.Date date = new Date();
				Object param = new java.sql.Timestamp(date.getTime());
				String updateQuery = "update cloud_services.user_table set last_login='" + param + "' where email='"
					+ user.getEmail() + "';";
				stmt.executeUpdate(updateQuery);
				
				user2.setFname(user.getFname());
		        user2.setLname(user.getLname());
		        user2.setUserid(userid);
		        user2.setEmail(user.getEmail());
		        user2.setAdmin(admin);
		        user2.setLast_login(last_login);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return user2;
	}
	
	public String lastLoginTime(String emailId) {
		ResultSet rs;
		String loginTime = "";
		try {
			stmt = conn.createStatement();
			String query = "Select lastlogin from cloud_services.user_table where emailid='" + emailId + "';";
			rs = stmt.executeQuery(query);
			if (rs.next()) {
				loginTime = rs.getString("user_last_login");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return loginTime;
	}


}
