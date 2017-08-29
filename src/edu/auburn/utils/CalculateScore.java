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
		
		return (float)editDistance.dynamicEditDistance(teacher, student);
	}
	
	public static ScoreModel getScoreModel(String studentAnswer, String teacherAnswer) {
		ScoreModel model = util.calculateScore(teacherAnswer, studentAnswer);
		return model;
	}
}
