package edu.auburn.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import edu.auburn.dao.IExamResultDao;
import edu.auburn.domain.ExamResult;
import edu.auburn.utils.JDBCUtil;

public class ExamResultDao implements IExamResultDao {

	@Override
	public boolean addResult(ExamResult result) {
		/*
		 * rid integer auto_increment primary key,
			uid integer,
			eid integer,
			answer varchar(2000)
		 */
		String sql = "insert into exam_result (uid, eid, answer) values (?,?,?)";
		Connection connection = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		try {
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setInt(1, result.getUid());
			ps.setInt(2, result.getEid());
			ps.setString(3, result.getAnswer());
			int r = ps.executeUpdate();
			return r > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JDBCUtil.close(connection, ps);
		}
	}

	@Override
	public ExamResult getResultByUidAndEid(int uid, int eid) {
		String sql = "select * from exam_result where eid = ? and uid = ?";
		Connection connection = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		try {
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setInt(1, eid);
			ps.setInt(2, uid);
			ResultSet rs = ps.executeQuery();
			ExamResult e = null;
			if (rs.next()) {
				e = new ExamResult();
				e.setEid(rs.getInt("eid"));
				e.setAnswer(rs.getString("answer"));
				e.setRid(rs.getInt("rid"));
				e.setUid(rs.getInt("uid"));
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
	public List<ExamResult> teacherCheckResultByEid(int eid) {
		String sql = "select * from exam_result where eid = ?";
		Connection connection = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		try {
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setInt(1, eid);
			ResultSet rs = ps.executeQuery();
			ExamResult e = null;
			List<ExamResult> result = new ArrayList<>();
			while (rs.next()) {
				e = new ExamResult();
				e.setEid(rs.getInt("eid"));
				e.setAnswer(rs.getString("answer"));
				e.setRid(rs.getInt("rid"));
				e.setUid(rs.getInt("uid"));
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

}
