package edu.auburn.service;

import java.util.List;

import edu.auburn.domain.ExamWord;

public interface IExamWordService {
	boolean addWord(ExamWord word);
	boolean delWordById(int fid);
	List<ExamWord> getAllWordsByEid(int eid);
	ExamWord getExamWordByFid(int fid);
	ExamWord getExamWordByEidAndWid(int eid, int wid);
}
