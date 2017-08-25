package edu.auburn.domain;

public class WordStudent {
	private int wsid;
	private int wid;
	private int sid;
	private int eid;
	private String answer;
	private float score;
	public int getWsid() {
		return wsid;
	}
	public void setWsid(int wsid) {
		this.wsid = wsid;
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
	public int getEid() {
		return eid;
	}
	public void setEid(int eid) {
		this.eid = eid;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}
	@Override
	public String toString() {
		return "WordStudent [wsid=" + wsid + ", wid=" + wid + ", sid=" + sid + ", eid=" + eid + ", answer=" + answer
				+ ", score=" + score + "]";
	}
	
}
