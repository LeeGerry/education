package edu.auburn.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import edu.auburn.dao.IWordStudentDao;
import edu.auburn.domain.WordStudent;
import edu.auburn.utils.JDBCUtil;

public class WordStudentDao implements IWordStudentDao {
	/**
	create table word_student(
		wsid integer auto_increment primary key,
		sid integer,
		wid integer,
		eid integer,
		answer varchar(200),
		score float 		
	)engine = myisam  default charset = utf8;
	 */
	@Override
	public boolean addStudentAnswerForWord(WordStudent ws) {
		String sql = "insert into word_student (sid, wid, eid, answer, score) values (?,?,?,?,?)";
		Connection connection = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		try {
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setInt(1, ws.getSid());
			ps.setInt(2, ws.getWid());
			ps.setInt(3, ws.getEid());
			ps.setString(4, ws.getAnswer());
			ps.setFloat(5, ws.getScore());
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
	public boolean updateAnswer(WordStudent ws) {
		String sql = "update word_student set answer = ?, score = ? where wid = ? and sid = ?";
		Connection connection = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		try {
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setString(1, ws.getAnswer());
			ps.setFloat(2, ws.getScore());
			ps.setInt(3, ws.getWid());
			ps.setInt(4, ws.getSid());
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
	public WordStudent getStudentAnswerModelBySidAndWid(int sid, int wid) {
		String sql = "select * from word_student where sid = ? and wid = ?";
		Connection connection = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		try {
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setInt(1, sid);
			ps.setInt(2, wid);
			ResultSet rs = ps.executeQuery();
			WordStudent ws = null;
			if (rs.next()) {
				ws = new WordStudent();
				ws.setWsid(rs.getInt("wsid"));
				ws.setAnswer(rs.getString("answer"));
				ws.setEid(rs.getInt("eid"));
				ws.setScore(rs.getFloat("score"));
				ws.setSid(rs.getInt("sid"));
				ws.setWid(rs.getInt("wid"));
			}
			return ws;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JDBCUtil.close(connection, ps);
		}
	}

	@Override
	public List<WordStudent> getStudentAnswerListBySidAndEid(int sid, int eid) {
		String sql = "select * from word_student where sid = ? and eid = ?";
		Connection connection = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		try {
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setInt(1, sid);
			ps.setInt(2, eid);
			ResultSet rs = ps.executeQuery();
			WordStudent ws = null;
			List<WordStudent> result = new ArrayList<>();
			while (rs.next()) {
				ws = new WordStudent();
				ws.setWsid(rs.getInt("wsid"));
				ws.setAnswer(rs.getString("answer"));
				ws.setEid(rs.getInt("eid"));
				ws.setScore(rs.getFloat("score"));
				ws.setSid(rs.getInt("sid"));
				ws.setWid(rs.getInt("wid"));
				result.add(ws);
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
	public List<WordStudent> getStudentAnswerListByEidAndWid(int eid, int wid) {
		String sql = "select * from word_student where eid = ? and wid = ?";
		Connection connection = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		try {
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setInt(1, eid);
			ps.setInt(2, wid);
			ResultSet rs = ps.executeQuery();
			WordStudent ws = null;
			List<WordStudent> result = new ArrayList<>();
			while (rs.next()) {
				ws = new WordStudent();
				ws.setWsid(rs.getInt("wsid"));
				ws.setAnswer(rs.getString("answer"));
				ws.setEid(rs.getInt("eid"));
				ws.setScore(rs.getFloat("score"));
				ws.setSid(rs.getInt("sid"));
				ws.setWid(rs.getInt("wid"));
				result.add(ws);
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
