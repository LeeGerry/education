package edu.auburn.domain;

import java.sql.Date;

public class EduUser {
/**
 * create table euser(
	uid integer auto_increment primary key,
	udate datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
	name varchar(50),
	password varchar(20),
	email varchar(50),
	utype integer
)engine = myisam  default charset = utf8;
 */
	private int uid;
	private String name;
	private String password;
	private String email;
	private int type;
	private java.sql.Date date;
	private String role;
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public EduUser() {
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(java.sql.Date date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "EduUser [uid=" + uid + ", name=" + name + ", password=" + password + ", email=" + email + ", type="
				+ type + ", date=" + date + "]";
	}
	public EduUser(int uid, String name, String password, String email, int type, Date date) {
		super();
		this.uid = uid;
		this.name = name;
		this.password = password;
		this.email = email;
		this.type = type;
		this.date = date;
	}
	
}
