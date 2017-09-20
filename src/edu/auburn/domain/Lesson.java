package edu.auburn.domain;

import java.sql.Date;

public class Lesson {
//	lid integer auto_increment primary key,
//	name varchar(50),
//	ldesc varchar(200),
//	udate datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
//	uid integer,
//	ltype integer
	private int lid;
	private String name;
	private String desc;
	private int uid;
	private int type;
	private Date date;
	private String uname;
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getLid() {
		return lid;
	}
	public void setLid(int lid) {
		this.lid = lid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "Lesson [lid=" + lid + ", name=" + name + ", desc=" + desc + ", uid=" + uid + ", type=" + type + "]";
	}
	
}
