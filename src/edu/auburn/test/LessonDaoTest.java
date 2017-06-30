package edu.auburn.test;

import java.sql.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.auburn.dao.ILessonDao;
import edu.auburn.dao.impl.LessonDao;
import edu.auburn.domain.Lesson;

public class LessonDaoTest {
	private ILessonDao dao;
	@Before
	public void init(){
		dao = new LessonDao();
	}
	@After
	public void des(){
		dao = null;
	}
	@Test
	public void testAdd(){
		Lesson lesson = new Lesson();
		lesson.setName("lesson1");
		lesson.setUid(4);
		lesson.setDesc("this is lesson1");
		lesson.setDate(new Date(System.currentTimeMillis()));
		lesson.setType(1);
		dao.addLesson(lesson);
	}
	@Test
	public void testDel(){
		dao.delLessonById(1);
	}
	@Test
	public void testGetLessonByUid(){
		System.out.println(dao.getLessonsByUid(4));
	}
}
