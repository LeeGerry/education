package edu.auburn.utils;

import edu.auburn.utils.yzal.MatchLetter;

public class CalculateScore {
	public static float getScore(String studentAnswer, String teacherAnswer) {
//		EDistanceWithOutputWithWeight ewww = new EDistanceWithOutputWithWeight();
//		double score = ewww.dynamicEditDistance(teacherAnswer, studentAnswer);
//		double result = 1-(score/Math.max(studentAnswer.length(), teacherAnswer.length()));
		
		MatchLetter ml = new MatchLetter();
		float distance =  ml.finalCalculate(teacherAnswer, studentAnswer);
		float score = 1-(distance/Math.max(studentAnswer.length(), teacherAnswer.length()));
		return score<0?0:score;
	}
}
