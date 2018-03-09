package edu.auburn.utils;

import java.util.List;

import edu.auburn.domain.ScoreModel;
import edu.auburn.utils.scal.EDistanceWithOutputWithWeight;
import edu.auburn.utils.scal.PreProcess;

public class CalculateScore {
	private static IScoreUtil util ;
	public static float getScore(String studentAnswer, String teacherAnswer) {
//		EDistanceWithOutputWithWeight ewww = new EDistanceWithOutputWithWeight();
//		double score = ewww.dynamicEditDistance(teacherAnswer, studentAnswer);
//		double result = 1-(score/Math.max(studentAnswer.length(), teacherAnswer.length()));
		
//		MatchLetter ml = new MatchLetter();
//		float distance =  ml.finalCalculate(teacherAnswer, studentAnswer);
//		float score = 1-(distance/Math.max(studentAnswer.length(), teacherAnswer.length()));
		
		
		
		EDistanceWithOutputWithWeight editDistance = new EDistanceWithOutputWithWeight();
		PreProcess prePro = new PreProcess();
		List<String> student = prePro.generateListOfStrings(studentAnswer);
		List<String> teacher = prePro.generateListOfStrings(teacherAnswer);
		float result = (float)editDistance.dynamicEditDistance(teacher, student);
		result = (float) Math.round(result * 10000) / 10000;
		return result;
	}
	
	public static ScoreModel getScoreModel(String studentAnswer, String teacherAnswer) {
		ScoreModel model = util.calculateScore(teacherAnswer, studentAnswer);
		return model;
	}
	
	public static String getPercentage(String studentAnswer, String teacherAnswer, float distance){
		float result = 1-(distance/Math.max(studentAnswer.length(), teacherAnswer.length()));
		result = (float)Math.round(result * 10000) / 100;
		String percentage = String.valueOf(result);
		return percentage+"%";
	}
}
