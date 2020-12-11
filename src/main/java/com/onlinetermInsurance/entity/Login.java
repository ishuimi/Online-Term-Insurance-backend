package com.onlinetermInsurance.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="LOGIN")
@Table(name="LOGIN")
public class Login {
	
	@Id
	@Column(name="l_id")
	int lId;
	@Column(name="username")
	String userName;
	@Column(name="password")
	String password;
	@Column(name="user_type")
	String userType;
	public int getlId() {
		return lId;
	}
	public void setlId(int lId) {
		this.lId = lId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public Login(int lId, String userName, String password, String userType) {
		super();
		this.lId = lId;
		this.userName = userName;
		this.password = password;
		this.userType = userType;
	}
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
