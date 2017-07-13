package edu.auburn.domain;

public class DisplayStudentExamResult {
	private String sAnswer;
	private String tAnswer;
	private float score;
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
