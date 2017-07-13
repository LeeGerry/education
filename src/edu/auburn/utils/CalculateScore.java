package edu.auburn.utils;

public class CalculateScore {
	public static float getScore(String studentAnswer, String teacherAnswer){
		return studentAnswer.equals(teacherAnswer)?1:0;
	}
}
