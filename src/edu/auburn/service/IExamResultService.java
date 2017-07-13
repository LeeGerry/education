package edu.auburn.service;

import java.util.List;

import edu.auburn.domain.DisplayStudentExamResult;
import edu.auburn.domain.ExamResult;
import edu.auburn.domain.ExamScore;

public interface IExamResultService {
	boolean addResult(ExamResult result);
	ExamResult getResultByUidAndEid(int uid, int eid);
	ExamScore getExamScoreByUidAndEid(int uid, int eid);
	List<DisplayStudentExamResult> getDisplayResult(int uid, int eid);
}
