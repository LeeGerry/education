package edu.auburn.test;

import org.junit.Test;

import edu.auburn.dao.IWordStudentDao;
import edu.auburn.dao.impl.WordStudentDao;

public class WordStudentTest {
	IWordStudentDao dao = new WordStudentDao();
	@Test
	public void testFindByLid(){
		System.out.println(dao.getStudentAnswerListByEidAndWid(1, 1));
	}
}
