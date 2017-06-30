package edu.auburn.service;

import java.util.List;

import edu.auburn.domain.LessonStudent;

public interface ILessonStudentService {
	List<LessonStudent> getLSByLid(int lid);
	List<LessonStudent> getLSBySid(int sid);
	boolean updateToTaByLidAndSid(int lid, int sid);
	boolean updateToStuByLidAndSid(int lid, int sid);
	
}
