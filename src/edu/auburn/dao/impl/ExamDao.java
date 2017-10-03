package edu.auburn.dao.impl;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.StringUtils;

import edu.auburn.dao.IExamDao;
import edu.auburn.dao.ILessonDao;
import edu.auburn.dao.IUserDao;
import edu.auburn.domain.EduUser;
import edu.auburn.domain.Exam;
import edu.auburn.domain.Lesson;
import edu.auburn.utils.JDBCUtil;

public class ExamDao implements IExamDao {

	@Override
	public boolean addExam(Exam exam) {
		// create table exam(
		// eid integer auto_increment primary key,
		// name varchar(50),
		// edesc varchar(200),
		// edue datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
		// etype integer,
		// lid integer,
		// uid integer
		// )engine = myisam default charset = utf8;
		String sql = "insert into exam (name, edesc, edue, etype, lid, uid, ispractice) values (?,?,?,?,?,?,?)";
		Connection connection = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		try {
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setString(1, exam.getName());
			ps.setString(2, exam.getEdesc());
			ps.setTimestamp(3, new Timestamp(exam.getEdue().getTime()));
			ps.setInt(4, exam.getEtype());
			ps.setInt(5, exam.getLid());
			ps.setInt(6, exam.getUid());
			ps.setInt(7, exam.getIfPractice());
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
	public boolean delExamById(int id) {
		String sql = "delete from exam where eid = ?";
		Connection connection = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		try {
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setInt(1, id);
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
	public List<Exam> getExamsByLid(int lid) {
		String sql = "select * from exam where lid = ? order by eid";
		Connection connection = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		try {
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setInt(1, lid);
			ResultSet rs = ps.executeQuery();
			Exam e = null;
			List<Exam> result = new ArrayList<>();
			ILessonDao ldao = new LessonDao();
			IUserDao udao = new UserDao();
			String lname = ldao.getLessonById(lid).getName();
			if (StringUtils.isNullOrEmpty(lname))
				lname = "";
			while (rs.next()) {
				e = new Exam();
				e.setEid(rs.getInt("eid"));
				e.setName(rs.getString("name"));
				e.setEdesc(rs.getString("edesc"));
				Timestamp ts = rs.getTimestamp("edue");
				e.setEdue(new java.util.Date(ts.getTime()));
				e.setEtype(rs.getInt("etype"));
				e.setIfPractice(rs.getInt("ispractice"));
				e.setLid(lid);
				e.setLname(lname);
				int uid = rs.getInt("uid");
				e.setUid(uid);
				EduUser u = udao.getUserById(uid);
				String uname = "";
				if (u != null && !StringUtils.isNullOrEmpty(u.getName())) {
					uname = u.getName();
				}
				e.setUname(uname);
				result.add(e);
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
	public Exam getExamById(int eid) {
		String sql = "select * from exam where eid = ?";
		Connection connection = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		try {
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setInt(1, eid);
			ResultSet rs = ps.executeQuery();
			Exam e = null;
			IUserDao udao = new UserDao();
			ILessonDao ldao = new LessonDao();
			if (rs.next()) {
				e = new Exam();
				e.setEid(rs.getInt("eid"));
				e.setName(rs.getString("name"));
				e.setEdesc(rs.getString("edesc"));
				Timestamp ts = rs.getTimestamp("edue");
				Date d = new Date(ts.getTime());
				e.setEdue(d);
				e.setEtype(rs.getInt("etype"));
				int lid = rs.getInt("lid");
				e.setIfPractice(rs.getInt("ispractice"));
				e.setLid(lid);
				Lesson l = ldao.getLessonById(lid);
				String lname = "";
				if (l != null && !StringUtils.isNullOrEmpty(l.getName())) {
					lname = l.getName();
				}
				e.setLname(lname);
				e.setUid(rs.getInt("uid"));
				EduUser u = udao.getUserById(lid);
				String uname = "";
				if (u != null && !StringUtils.isNullOrEmpty(u.getName())) {
					uname = u.getName();
				}
				e.setUname(uname);
			}
			return e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JDBCUtil.close(connection, ps);
		}
	}

	@Override
	public boolean updateDueDateAndTypeByEid(long date, int type, int eid, int keyboard) {
		String sql = "update exam set edue = ?, ispractice = ?, etype = ? where eid = ?";
		
		Connection connection = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		try {
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setTimestamp(1, new Timestamp(date));
			ps.setInt(2, type);
			ps.setInt(3, keyboard);
			ps.setInt(4, eid);
			int result = ps.executeUpdate();
			return result > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JDBCUtil.close(connection, ps);
		}
	}

}
