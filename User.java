package edu.sjsu.cmpe282.domain;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnore;

public class User {
	
	private String fname;
	private String lname;
	private String email;
	private String password;
	private Date lastlogin;
	private int userid;
	private boolean isAdmin;
	private int admin;
	private String last_login;
		
	public int getAdmin() {
		return admin;
	}

	@JsonIgnore
	public void setAdmin(int admin) {
		this.admin = admin;
	}


	public boolean isAdmin() {
		return isAdmin;
	}


	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}


	public User() {
		super();
	}
	
		
	public User(String firstName, String lastName, String email, String password) {
		super();
		this.fname = firstName;
		this.lname = lastName;
		this.email = email;
		this.password = password;
	}


	public int getUserid() {
		return userid;
	}


	public void setUserid(int userid) {
		this.userid = userid;
	}
	

	public String getFname() {
		return fname;
	}


	public void setFname(String fname) {
		this.fname = fname;
	}


	public String getLname() {
		return lname;
	}


	public void setLname(String lname) {
		this.lname = lname;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Date getLastlogin() {
		return lastlogin;
	}


	public void setLastlogin(Date lastlogin) {
		this.lastlogin = lastlogin;
	}


	@Override
	public String toString() {
		return "User [firstName=" + fname + ", lastName=" + lname
				+ ", email=" + email + ", password=" + password + ", loginTime="
				+ lastlogin + "]";
	}

	public String getLast_login() {
		return last_login;
	}

	public void setLast_login(String last_login) {
		this.last_login = last_login;
	}
}
