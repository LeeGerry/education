package edu.auburn.service.impl;

import java.util.List;

import edu.auburn.dao.ILessonStudentDao;
import edu.auburn.dao.impl.LessonStudentDao;
import edu.auburn.domain.LessonStudent;
import edu.auburn.service.ILessonStudentService;

public class LessonStudentService implements ILessonStudentService {
	ILessonStudentDao sd = new LessonStudentDao();
	@Override
	public List<LessonStudent> getLSByLid(int lid) {
		// TODO Auto-generated method stub
		return sd.getLSByLid(lid);
	}

	@Override
	public List<LessonStudent> getLSBySid(int sid) {
		// TODO Auto-generated method stub
		return sd.getLSBySid(sid);
	}

	@Override
	public boolean updateToTaByLidAndSid(int lid, int sid) {
		// TODO Auto-generated method stub
		return sd.updateToTaByLidAndSid(lid, sid);
	}

	@Override
	public boolean updateToStuByLidAndSid(int lid, int sid) {
		// TODO Auto-generated method stub
		return sd.updateToStuByLidAndSid(lid, sid);
	}

}
