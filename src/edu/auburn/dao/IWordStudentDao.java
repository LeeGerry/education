package edu.auburn.dao;

import java.util.List;

import edu.auburn.domain.WordStudent;

public interface IWordStudentDao {
	boolean addStudentAnswerForWord(WordStudent ws);
	boolean updateAnswer(WordStudent ws);
	WordStudent getStudentAnswerModelBySidAndWid(int sid, int wid);
	List<WordStudent> getStudentAnswerListBySidAndEid(int sid, int eid);
	List<WordStudent> getStudentAnswerListByEidAndWid(int eid, int wid);
}
