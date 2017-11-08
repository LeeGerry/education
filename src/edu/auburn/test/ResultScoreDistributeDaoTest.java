package edu.auburn.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.auburn.dao.IResultScoreDistribute;
import edu.auburn.dao.impl.ResultScoreDistributeDao;
import edu.auburn.domain.ResultScoreDistribute;

public class ResultScoreDistributeDaoTest {
	private IResultScoreDistribute dao;
	@Before
	public void init(){
		dao = new ResultScoreDistributeDao();
	}
	@After
	public void des(){
		dao = null;
	}
	@Test
	public void testTeacherCheck(){
		ResultScoreDistribute e = dao.getDistribute(1);
		System.out.println(e);

	}
}
