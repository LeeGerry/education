package edu.auburn.service.impl;

import java.util.List;

import edu.auburn.dao.ILessonDao;
import edu.auburn.dao.impl.LessonDao;
import edu.auburn.domain.Lesson;
import edu.auburn.service.ILessonService;

public class LessonService implements ILessonService{
	private ILessonDao dao = new LessonDao();
	@Override
	public boolean addLesson(Lesson lesson) {
		// TODO Auto-generated method stub
		return dao.addLesson(lesson);
	}

	@Override
	public boolean delLessonById(int id) {
		// TODO Auto-generated method stub
		return dao.delLessonById(id);
	}

	@Override
	public List<Lesson> getLessonsByUid(int uid) {
		// TODO Auto-generated method stub
		return dao.getLessonsByUid(uid);
	}

	@Override
	public Lesson getLessonByLid(int lid) {
		// TODO Auto-generated method stub
		return dao.getLessonById(lid);
	}

	@Override
	public List<Lesson> getAllLessons() {
		// TODO Auto-generated method stub
		return dao.getAllLessons();
	}

	@Override
	public List<Lesson> getAllLessonsOrderByName(int uid) {
		// TODO Auto-generated method stub
		return dao.getAllLessonsOrderedByName(uid);
	}

}
