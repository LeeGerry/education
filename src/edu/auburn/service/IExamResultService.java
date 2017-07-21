package edu.auburn.service;

import java.util.List;

import edu.auburn.domain.ExamResult;

public interface IExamResultService {
	boolean addResult(int uid, int eid, String sAnswers);
	ExamResult getResultByUidAndEid(int uid, int eid);
	List<ExamResult> teacherCheckResultByEid(int eid);
}
