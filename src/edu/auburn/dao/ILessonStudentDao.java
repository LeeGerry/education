package edu.auburn.dao;

import java.util.List;

import edu.auburn.domain.LessonStudent;

public interface ILessonStudentDao {
	List<LessonStudent> getLSByLid(int lid);
	List<LessonStudent> getLSBySid(int sid);
	boolean updateToTaByLidAndSid(int lid, int sid);
	boolean updateToStuByLidAndSid(int lid, int sid);
	boolean checkRegLesson(int lid, int uid);
	boolean addLessonStudent(int lid, int sid);
	boolean delLessonStudent(int lid, int sid);
	LessonStudent getRoleByLidAndUid(int lid, int uid);
}
