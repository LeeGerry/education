package edu.auburn.domain;

import java.util.List;

public class ExamResult {
	private int rid;
	private int uid;
	private int eid;
	private List<String> tAnswers;
	private List<String> sAnswers;
	private List<Float> scoreList;
	private float total;
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getEid() {
		return eid;
	}
	public void setEid(int eid) {
		this.eid = eid;
	}
	public List<String> gettAnswers() {
		return tAnswers;
	}
	public void settAnswers(List<String> tAnswers) {
		this.tAnswers = tAnswers;
	}
	public List<String> getsAnswers() {
		return sAnswers;
	}
	public void setsAnswers(List<String> sAnswers) {
		this.sAnswers = sAnswers;
	}
	public List<Float> getScoreList() {
		return scoreList;
	}
	public void setScoreList(List<Float> scoreList) {
		this.scoreList = scoreList;
	}
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	@Override
	public String toString() {
		return "ExamResult [rid=" + rid + ", uid=" + uid + ", eid=" + eid + ", tAnswers=" + tAnswers + ", sAnswers="
				+ sAnswers + ", scoreList=" + scoreList + ", total=" + total + "]";
	}
	
}
