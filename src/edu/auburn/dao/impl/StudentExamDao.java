package edu.auburn.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import edu.auburn.dao.IStudentExamDao;
import edu.auburn.domain.ExamStudent;
import edu.auburn.utils.JDBCUtil;

public class StudentExamDao implements IStudentExamDao {

	@Override
	public boolean checkIfExamTakenByStudent(int eid, int sid) {
		String sql = "select * from student_exam where eid = ? and sid = ?";
		Connection connection = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		try {
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setInt(1, eid);
			ps.setInt(2, sid);
			ResultSet rs = ps.executeQuery();
			if (!rs.next())
				return false;
			return rs.getInt("taken") != 0;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JDBCUtil.close(connection, ps);
		}
	}

	@Override
	public boolean takeExam(int eid, int sid) {
		String sql = "insert into student_exam (eid, sid, taken) values (?,?,?)";
		Connection connection = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		try {
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setInt(1, eid);
			ps.setInt(2, sid);
			ps.setInt(3, 1);
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
