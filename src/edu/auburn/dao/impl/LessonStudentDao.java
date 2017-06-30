package edu.auburn.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.StringUtils;

import edu.auburn.dao.ILessonDao;
import edu.auburn.dao.ILessonStudentDao;
import edu.auburn.dao.IUserDao;
import edu.auburn.domain.EduUser;
import edu.auburn.domain.Lesson;
import edu.auburn.domain.LessonStudent;
import edu.auburn.utils.JDBCUtil;

public class LessonStudentDao implements ILessonStudentDao {

//	id integer auto_increment primary key,
//	lid integer,
//	sid integer,
//	role integer
	@Override
	public List<LessonStudent> getLSByLid(int lid) {
		String sql = "select * from lesson_student where lid = ? order by sid";
		Connection connection = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		try {
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setInt(1, lid);
			ResultSet rs = ps.executeQuery();
			LessonStudent ls = null;
			List<LessonStudent> result = new ArrayList<>();
			IUserDao udao = new UserDao();
			ILessonDao ldao = new LessonDao();
			String lname = ldao.getLessonById(lid).getName();
			if (StringUtils.isNullOrEmpty(lname))
				lname = "";
			while (rs.next()) {
				ls = new LessonStudent();
				ls.setId(rs.getInt("id"));
				ls.setLid(lid);
				int uid = rs.getInt("sid");
				ls.setSid(uid);
				ls.setLname(lname);
				EduUser u = udao.getUserById(uid);
				String uname = "";
				if(null != u && !StringUtils.isNullOrEmpty(u.getName())){
					uname = u.getName();
				}
				ls.setSname(uname);
				int type = rs.getInt("stype");
				ls.setType(type);
				ls.setRole(type==1?"ta":"student");
				result.add(ls);
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JDBCUtil.close(connection, ps);
		}
	}

	@Override
	public List<LessonStudent> getLSBySid(int sid) {
		String sql = "select * from lesson_student where sid = ? order by lid";
		Connection connection = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		try {
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setInt(1, sid);
			ResultSet rs = ps.executeQuery();
			LessonStudent ls = null;
			List<LessonStudent> result = new ArrayList<>();
			IUserDao udao = new UserDao();
			ILessonDao ldao = new LessonDao();
			EduUser eu = udao.getUserById(sid);
			String uname = "";
			if(null != eu && !StringUtils.isNullOrEmpty(eu.getName())){
				uname = eu.getName();
			}
			while (rs.next()) {
				ls = new LessonStudent();
				ls.setId(rs.getInt("id"));
				int lid = rs.getInt("lid");
				ls.setLid(lid);
				ls.setSid(sid);
				String lname = "";
				Lesson l = ldao.getLessonById(lid);
				if(null != l && !StringUtils.isNullOrEmpty(l.getName())){
					lname = l.getName();
				}
				ls.setLname(lname);
				ls.setSname(uname);
				int type = rs.getInt("stype");
				ls.setType(type);
				ls.setRole(type==1?"ta":"student");
				result.add(ls);
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JDBCUtil.close(connection, ps);
		}
	}

	@Override
	public boolean updateToTaByLidAndSid(int lid, int sid) {
		return changeType(lid, sid, 1);
	}

	private boolean changeType(int lid, int sid, int type) {
		String sql = "update lesson_student set stype = ? where lid = ? and sid = ?";
		Connection connection = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		try {
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setInt(1, type);
			ps.setInt(2, lid);
			ps.setInt(3, sid);
			int result = ps.executeUpdate();
			return result > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JDBCUtil.close(connection, ps);
		}
	}

	@Override
	public boolean updateToStuByLidAndSid(int lid, int sid) {
		return changeType(lid, sid, 2);
	}

}
