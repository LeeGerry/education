package edu.auburn.test;

import org.junit.Test;

import edu.auburn.dao.ILessonStudentDao;
import edu.auburn.dao.impl.LessonStudentDao;

public class LessonStudentDaoTest {
	ILessonStudentDao dao = new LessonStudentDao();
	@Test
	public void testFindByLid(){
		System.out.println(dao.getLSByLid(10));
	}
	@Test
	public void testFindBySid(){
		System.out.println(dao.getLSBySid(6));
	}
	@Test
	public void testCheckReg(){
		System.out.println(dao.checkRegLesson(15, 6));
	}
}
