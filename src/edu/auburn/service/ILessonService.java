package edu.auburn.service;

import java.util.List;

import edu.auburn.domain.Lesson;

public interface ILessonService {
	boolean addLesson(Lesson lesson);
	boolean delLessonById(int id);
	List<Lesson> getLessonsByUid(int uid);
	Lesson getLessonByLid(int lid);
}
