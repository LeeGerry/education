package edu.auburn.dao;

import java.util.List;

import edu.auburn.domain.Exam;

public interface IExamDao {
	boolean addExam(Exam exam);
	boolean delExamById(int id);
	List<Exam> getExamsByLid(int Lid);
	Exam getExamById(int eid);
}
