package edu.auburn.test;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.auburn.dao.IExamDao;
import edu.auburn.dao.impl.ExamDao;
import edu.auburn.domain.Exam;

public class ExamDaoTest {
	private IExamDao dao;
	@Before
	public void init(){
		dao = new ExamDao();
	}
	@After
	public void des(){
		dao = null;
	}
	@Test
	public void testUpdateDueByEid(){
		//dao.updateDueDateByEid(System.currentTimeMillis(), 1);
	}
	@Test
	public void testGetExamById(){
		Exam e = dao.getExamById(1);
		Date d = e.getEdue();
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = f.format(d);
		System.out.println(date);
	}
}
