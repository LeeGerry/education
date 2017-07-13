package edu.auburn.dao;

import edu.auburn.domain.ExamResult;

public interface IExamResultDao {
	boolean addResult(ExamResult result);
	ExamResult getResultByUidAndEid(int uid, int eid);
}
