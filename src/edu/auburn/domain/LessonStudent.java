package edu.auburn.domain;

public class LessonStudent {
	private int lid;
	private String lname;
	private int sid;
	private String sname;
	private int type;
	private String role;
	private int id;
	public int getLid() {
		return lid;
	}
	public void setLid(int lid) {
		this.lid = lid;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "LessonStudent [lid=" + lid + ", lname=" + lname + ", sid=" + sid + ", sname=" + sname + ", type=" + type
				+ ", role=" + role + ", id=" + id + "]";
	}
}
