package edu.auburn.dao;

import java.util.List;

import edu.auburn.domain.ExamResult;

public interface IExamResultDao {
	boolean addResult(ExamResult result);
	boolean deleteResult(int uid, int eid);
	ExamResult getResultByUidAndEid(int uid, int eid);
	List<ExamResult> teacherCheckResultByEid(int eid);
	
}
