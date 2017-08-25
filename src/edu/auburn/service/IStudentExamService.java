package edu.auburn.service;

public interface IStudentExamService {
	boolean checkIfExamTakenByStudent(int eid, int sid);
	boolean takeExam(int eid, int sid);
}
