package edu.auburn.domain;

public class ExamStudent {
	private int sid;
	private int eid;
	private boolean taken;
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public int getEid() {
		return eid;
	}
	public void setEid(int eid) {
		this.eid = eid;
	}
	public boolean isTaken() {
		return taken;
	}
	public void setTaken(boolean taken) {
		this.taken = taken;
	}
	@Override
	public String toString() {
		return "ExamStudent [sid=" + sid + ", eid=" + eid + ", taken=" + taken + "]";
	}
	
}
