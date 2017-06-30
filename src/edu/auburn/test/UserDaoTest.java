package edu.auburn.test;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mysql.jdbc.StringUtils;

import edu.auburn.dao.IUserDao;
import edu.auburn.dao.impl.UserDao;
import edu.auburn.domain.EduUser;
import edu.auburn.utils.LessonFileType;

public class UserDaoTest {
	/**
	 * 	boolean addUser(EduUser user);
		boolean delUserById(int id);
		boolean updateUserTypeById(int id, int type);
		List<EduUser> getAll();
		boolean checkUserByEmailAndPwd(String email, String pwd);
		EduUser getUserByEmail(String email);
		boolean checkUserEmailExist(String email);
	 */
	private IUserDao dao = null;
	@Before
	public void preInit(){
		dao = new UserDao();
	}
	@After
	public void post(){
		dao = null;
	}
	@Test
	public void checkUserByEmailAndPwd(){
		System.out.println(dao.checkUserByEmailAndPwd("admin@auburn.edu", "sstu"));
	}
	@Test
	public void testAdd(){
		EduUser user = new EduUser(12, "stu", "stu", "admin@auburn.edu", 1, new java.sql.Date(System.currentTimeMillis()));
		System.out.println(dao.addUser(user));
	}
	@Test
	public void getUserByEmail(){
		System.out.println(dao.getUserByEmail("ss@ta.edu"));
	}
	@Test
	public void checkUserEmailExist(){
		System.out.println(dao.checkUserEmailExist("ss1@ta.edu"));
	}
	@Test
	public void testGetAll(){
		System.out.println(dao.getAll());
	}
	@Test
	public void testDeleteUserById(){
		dao.delUserById(1);
	}
	@Test
	public void updateUserTypeById(){
		dao.updateUserTypeById(2, 2);
	}
	@Test
	public void getUserByName(){
		System.out.println(LessonFileType.PDF);
//		System.out.println(dao.getUserByName("teacher"));
	}
}
