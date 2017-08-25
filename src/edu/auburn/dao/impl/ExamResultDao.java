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
	answer varchar(2000),
	score varchar(500),
	total float
		 */
//		StringBuilder sbAnswer = new StringBuilder();
//		StringBuilder sbScore = new StringBuilder();
//		List<String> studentAnswers = result.getsAnswers();
//		List<Float> scoreList = result.getScoreList();
//		
//		for(int i = 0; i<studentAnswers.size(); i++){
//			sbAnswer.append(studentAnswers.get(i)).append("/");
//			sbScore.append(scoreList.get(i)+"").append("/");
//		}
//		sbAnswer.deleteCharAt(sbAnswer.length()-1);
//		sbScore.deleteCharAt(sbScore.length()-1);
//		String sAnswer = sbAnswer.toString();
//		String score = sbScore.toString();
		String sql = "insert into exam_result (uid, eid, total) values (?,?,?)";
		Connection connection = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		try {
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setInt(1, result.getUid());
			ps.setInt(2, result.getEid());
			ps.setFloat(3, result.getTotal());
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
				e.setRid(rs.getInt("rid"));
				e.setUid(rs.getInt("uid"));
				e.setTotal(rs.getFloat("total"));
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
		String sql = "select * from exam_result where eid = ? order by uid";
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
				e.setRid(rs.getInt("rid"));
				e.setUid(rs.getInt("uid"));
				e.setTotal(rs.getFloat("total"));
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
	public boolean deleteResult(int uid, int eid) {
		String sql = "delete from exam_result where uid = ? and eid = ?";
		Connection connection = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		try {
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setInt(1, uid);
			ps.setInt(2, eid);
			int r = ps.executeUpdate();
			return r > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JDBCUtil.close(connection, ps);
		}
	}

}
