package com.onlinetermInsurance.entity;

import java.sql.Date;


import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

@Entity(name="USER_DETAILS")
@Table(name="USER_DETAILS")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="user_id")
	private int userId;//user id auto creation
	@Column(name="first_name")
	private String fname;//name
	@Column(name="last_name")
	private String lname;
	@Column(name="phone_no")
	private Long phoneNo;//phone
	@Embedded
	Address ad;//address is embedded with address class because no need for address id 
	@Column(name="date_of_birth")
	Date dob;//Date of birth
	@Column(name="aadhar_no")
	private String aadhar;
	// sources in java getter setter AND constructors
	public User() {
		super();
	}
	public User(String fname, String lname, Long i, Address ad, Date dob, String aadhar) {
		super();
		//UserId = userId;
		this.fname = fname;
		this.lname = lname;
		this.phoneNo = i;
		this.ad = ad;
		this.dob = dob;
		this.aadhar = aadhar;
	}

	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		userId = userId;
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
	public Long getPhoneno() {
		return phoneNo;
	}
	public void setPhoneno(Long phoneno) {
		this.phoneNo = phoneno;
	}
	public Address getAd() {
		return ad;
	}
	public void setAd(Address ad) {
		this.ad = ad;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getAadhar() {
		return aadhar;
	}
	public void setAadhar(String aadhar) {
		this.aadhar = aadhar;
	}
	@Override
	public String toString() {
		return "User [UserId=" + userId + ", fname=" + fname + ", lname=" + lname + ", phoneno=" + phoneNo + ", ad="
				+ ad + ", dob=" + dob + ", aadhar=" + aadhar + "]";
	}
	
	
	
	
	

}
