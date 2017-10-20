package edu.auburn.dao;

import java.util.List;

import edu.auburn.domain.ExamWord;

public interface IExamWordDao {
	boolean addWord(ExamWord word);
	boolean delWordById(int fid);
	List<ExamWord> getAllWordsByEid(int eid);
	ExamWord getExamWordByVid(int fid);
	ExamWord getExamWordByEidAndWid(int eid, int wid);
}
