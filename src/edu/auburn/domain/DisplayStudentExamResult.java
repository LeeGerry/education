package edu.auburn.domain;

public class DisplayStudentExamResult {
	private int wid;
	private int sid;
	private String sAnswer;
	private String tAnswer;
	private float score;
	private String percentage;
	
	public String getPercentage() {
		return percentage;
	}
	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}
	public int getWid() {
		return wid;
	}
	public void setWid(int wid) {
		this.wid = wid;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getsAnswer() {
		return sAnswer;
	}
	public void setsAnswer(String sAnswer) {
		this.sAnswer = sAnswer;
	}
	public String gettAnswer() {
		return tAnswer;
	}
	public void settAnswer(String tAnswer) {
		this.tAnswer = tAnswer;
	}
	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}
	@Override
	public String toString() {
		return "DisplayStudentExamResult [sAnswer=" + sAnswer + ", tAnswer=" + tAnswer + ", score=" + score + "]";
	}
	
}
