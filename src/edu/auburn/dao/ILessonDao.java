package edu.auburn.dao;

import java.util.List;

import edu.auburn.domain.Lesson;

public interface ILessonDao {
	boolean addLesson(Lesson lesson);
	boolean delLessonById(int id);
	List<Lesson> getLessonsByUid(int uid);
	Lesson getLessonById(int lid);
	List<Lesson> getAllLessons();
	List<Lesson> getAllLessonsOrderedByName(int uid);
}
