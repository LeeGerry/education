package edu.auburn.dao;

import java.util.List;

import edu.auburn.domain.EduUser;

public interface IUserDao {
	boolean addUser(EduUser user);
	boolean delUserById(int id);
	boolean updateUserTypeById(int id, int type);
	List<EduUser> getAll();
	boolean checkUserByEmailAndPwd(String email, String pwd);
	EduUser getUserByEmail(String email);
	EduUser getUserByName(String name);
	boolean checkUserEmailExist(String email);
	EduUser getUserById(int uid);
}
