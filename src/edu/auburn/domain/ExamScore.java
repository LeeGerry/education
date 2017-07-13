package edu.auburn.domain;

import java.util.List;

public class ExamScore {
	private List<ExamWord> words;
	private List<String> answers;
	private List<Float> score;
	private Exam exam;
	private EduUser student;
	public List<ExamWord> getWords() {
		return words;
	}
	public void setWords(List<ExamWord> words) {
		this.words = words;
	}
	public List<String> getAnswers() {
		return answers;
	}
	public void setAnswers(List<String> answers) {
		this.answers = answers;
	}
	public List<Float> getScore() {
		return score;
	}
	public void setScore(List<Float> score) {
		this.score = score;
	}
	public Exam getExam() {
		return exam;
	}
	public void setExam(Exam exam) {
		this.exam = exam;
	}
	public EduUser getStudent() {
		return student;
	}
	public void setStudent(EduUser student) {
		this.student = student;
	}
	@Override
	public String toString() {
		return "ExamScore [words=" + words + ", answers=" + answers + ", score=" + score + ", exam=" + exam
				+ ", student=" + student + "]";
	}
}
