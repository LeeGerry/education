package edu.auburn.utils;

import edu.auburn.domain.ScoreModel;

public interface IScoreUtil {
	ScoreModel calculateScore(String teacherAnswer, String studentAnswer);
}
