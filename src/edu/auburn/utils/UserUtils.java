package edu.auburn.utils;

public class UserUtils {
	/**
	 * get role by type
	 * 
	 * @param type
	 * @return the role of user
	 */
	public static String getRoleByType(int type) {
		String result = "";
		switch (type) {
		case 1:
			result = "Admin";
			break;
		case 2:
			result = "Teacher";
			break;
		case 3:
			result = "Ta";
			break;
		case 4:
			result = "Student";
			break;
		default:
			result = "non";
			break;
		}
		return result;
	}
}
