package edu.auburn.domain;

import java.util.Date;

public class Exam {
//	create table exam(
//			eid integer auto_increment primary key,
//			name varchar(50),
//			edesc varchar(200),
//			edue datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
//			etype integer,
//			lid integer,
//			uid integer
//		)engine = myisam  default charset = utf8;
	private int eid;
	private String name;
	private String edesc;
	private Date edue;
	private int etype;
	private int lid;
	private int uid;
	private String lname;
	private String uname;
	private int ifPractice;
	public int getIfPractice() {
		return ifPractice;
	}
	public void setIfPractice(int ifPractice) {
		this.ifPractice = ifPractice;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public int getEid() {
		return eid;
	}
	public void setEid(int eid) {
		this.eid = eid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEdesc() {
		return edesc;
	}
	public void setEdesc(String edesc) {
		this.edesc = edesc;
	}
	public Date getEdue() {
		return edue;
	}
	public void setEdue(Date edue) {
		this.edue = edue;
	}
	public int getEtype() {
		return etype;
	}
	public void setEtype(int etype) {
		this.etype = etype;
	}
	public int getLid() {
		return lid;
	}
	public void setLid(int lid) {
		this.lid = lid;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	@Override
	public String toString() {
		return "Exam [eid=" + eid + ", name=" + name + ", edesc=" + edesc + ", edue=" + edue + ", etype=" + etype
				+ ", lid=" + lid + ", uid=" + uid + "]";
	}
	
}
