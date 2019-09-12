package com.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "student")
public class Student {

	@Id
	@GeneratedValue
	private Long stdid;
	private String fname;
	private String lname;
	private String email;

	public Long getStdid() {
		return stdid;
	}

	public void setStdid(Long stdid) {
		this.stdid = stdid;
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

	public Student() {
		super();
	}

	public Student(Long stdid) {
		super();
		this.stdid = stdid;
	}

	@Override
	public String toString() {
		return "Student [stdid=" + stdid + ", fname=" + fname + ", lname=" + lname + ", email=" + email + "]";
	}
}
