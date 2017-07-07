package edu.auburn.service;

import java.util.List;

import edu.auburn.domain.Exam;

public interface IExamService {
	boolean addExam(Exam exam);
	boolean delExamById(int id);
	List<Exam> getExamsByLid(int Lid);
	Exam getExamById(int eid);
}
