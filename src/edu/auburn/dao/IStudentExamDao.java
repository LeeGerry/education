package edu.auburn.dao;

public interface IStudentExamDao {
	boolean checkIfExamTakenByStudent(int eid, int sid);
	boolean takeExam(int eid, int sid);
}
