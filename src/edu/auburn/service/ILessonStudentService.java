package edu.auburn.service;

import java.util.List;

import edu.auburn.domain.LessonStudent;

public interface ILessonStudentService {
	List<LessonStudent> getLSByLid(int lid);
	List<LessonStudent> getLSBySid(int sid);
	boolean updateToTaByLidAndSid(int lid, int sid);
	boolean updateToStuByLidAndSid(int lid, int sid);
	/**
	 * check if student with uid has registered lesson with lid
	 * @param lid
	 * @param uid
	 * @return
	 */
	boolean checkRegLesson(int lid, int uid);
	boolean addLessonStudent(int lid, int sid);
	boolean delLessonStudent(int lid, int sid);
	LessonStudent getLSByUidAndLid(int uid, int lid);
}
