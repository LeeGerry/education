package edu.auburn.test;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.auburn.dao.IExamResultDao;
import edu.auburn.dao.impl.ExamResultDao;
import edu.auburn.domain.ExamResult;

public class ExamResultDaoTest {
	private IExamResultDao dao;
	@Before
	public void init(){
		dao = new ExamResultDao();
	}
	@After
	public void des(){
		dao = null;
	}
	@Test
	public void testTeacherCheck(){
		List<ExamResult> e = dao.teacherCheckResultByEid(1);
		System.out.println(e);
		//[ExamResult [rid=1, uid=3, eid=1, tAnswers=null, sAnswers=null, scoreList=null, total=1.0], 
//		ExamResult [rid=3, uid=4, eid=1, tAnswers=null, sAnswers=null, scoreList=null, total=11.0], 
//		ExamResult [rid=2, uid=5, eid=1, tAnswers=null, sAnswers=null, scoreList=null, total=6.0], 
//		ExamResult [rid=4, uid=6, eid=1, tAnswers=null, sAnswers=null, scoreList=null, total=13.0], 
//		ExamResult [rid=5, uid=7, eid=1, tAnswers=null, sAnswers=null, scoreList=null, total=15.0], 
//		ExamResult [rid=6, uid=8, eid=1, tAnswers=null, sAnswers=null, scoreList=null, total=3.0]]

	}
}
