package edu.auburn.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.StringUtils;

import edu.auburn.dao.ILessonDao;
import edu.auburn.dao.IUserDao;
import edu.auburn.domain.Lesson;
import edu.auburn.utils.JDBCUtil;

public class LessonDao implements ILessonDao {

	@Override
	public boolean addLesson(Lesson lesson) {
		// lid integer auto_increment primary key,
		// name varchar(50),
		// ldesc varchar(200),
		// udate datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
		// uid integer,
		// ltype integer
		String sql = "insert into lesson (name, ldesc, udate, uid, ltype) values (?,?,?,?,?)";
		Connection connection = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		try {
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setString(1, lesson.getName());
			ps.setString(2, lesson.getDesc());
			ps.setDate(3, lesson.getDate());
			ps.setInt(4, lesson.getUid());
			ps.setInt(5, lesson.getType());
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
	public boolean delLessonById(int lid) {
		String sql = "delete from lesson where lid = ?";
		Connection connection = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		try {
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setInt(1, lid);
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
	public List<Lesson> getLessonsByUid(int uid) {
		String sql = "select * from lesson where uid = ? order by lid";
		Connection connection = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		try {
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setInt(1, uid);
			ResultSet rs = ps.executeQuery();
			Lesson l = null;
			List<Lesson> result = new ArrayList<>();
			IUserDao udao = new UserDao();
			String uname = udao.getUserById(uid).getName();
			if (StringUtils.isNullOrEmpty(uname))
				uname = "";
			while (rs.next()) {
				l = new Lesson();
				// String sql = "insert into lesson (name, ldesc, udate, uid,
				// ltype) values (?,?,?,?,?)";
				l.setLid(rs.getInt("lid"));
				l.setName(rs.getString("name"));
				l.setDesc(rs.getString("ldesc"));
				l.setDate(rs.getDate("udate"));
				l.setUid(rs.getInt("uid"));
				l.setType(rs.getInt("ltype"));
				l.setUname(uname);
				result.add(l);
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
	public Lesson getLessonById(int lid) {
		String sql = "select * from lesson where lid = ?";
		Connection connection = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		try {
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setInt(1, lid);
			ResultSet rs = ps.executeQuery();
			Lesson l = null;
			IUserDao udao = new UserDao();
			if (rs.next()) {
				l = new Lesson();
				// String sql = "insert into lesson (name, ldesc, udate, uid,
				// ltype) values (?,?,?,?,?)";
				l.setLid(rs.getInt("lid"));
				l.setName(rs.getString("name"));
				l.setDesc(rs.getString("ldesc"));
				l.setDate(rs.getDate("udate"));
				int uid = rs.getInt("uid");
				l.setUid(uid);
				String uname = udao.getUserById(uid).getName();
				if (StringUtils.isNullOrEmpty(uname))
					uname = "";
				l.setUname(uname);
				l.setType(rs.getInt("ltype"));
				l.setUname(uname);
				
			}
			return l;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JDBCUtil.close(connection, ps);
		}
	}

}
