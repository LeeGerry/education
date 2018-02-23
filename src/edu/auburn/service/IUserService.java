package edu.auburn.service;

import java.util.List;

import edu.auburn.domain.EduUser;

public interface IUserService {
	boolean addUser(EduUser user);
	boolean delUserById(int id);
	boolean updateUserTypeById(int id, int type);
	List<EduUser> getAll();
	boolean checkUserByEmailAndPwd(String email, String pwd);
	EduUser getUserByEmail(String email);
	EduUser getUserByName(String name);
	EduUser getUserById(int uid);
	boolean addVerifyCodeByEmail(String email, int code);
	boolean delVerifyCodeByEmail(String email);
	boolean checkVerifyCodeByEmail(String email, int code);
	boolean updatePassword(String mail, String confirm);
}
