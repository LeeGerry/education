package edu.auburn.domain;

public class ExamTeacherResult {
	private String stuName;
	private float score;
	private int sid;
	private int eid;
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}
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
	@Override
	public String toString() {
		return "ExamTeacherResult [stuName=" + stuName + ", score=" + score + ", sid=" + sid + ", eid=" + eid + "]";
	}
	
}
