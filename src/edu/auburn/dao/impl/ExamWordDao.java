package edu.auburn.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import edu.auburn.dao.IExamWordDao;
import edu.auburn.domain.ExamVideo;
import edu.auburn.domain.ExamWord;
import edu.auburn.utils.JDBCUtil;

public class ExamWordDao implements IExamWordDao {

	@Override
	public boolean addWord(ExamWord word) {
		String sql = "insert into exam_word (pronunciation, path, ftype, fdesc, eid, name) values (?,?,?,?,?,?)";
		Connection connection = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		try {
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setString(1, word.getPron());
			ps.setString(2, word.getPath());
			ps.setString(3, word.getType());
			ps.setString(4, word.getDesc());
			ps.setInt(5, word.getEid());
			ps.setString(6, word.getName());
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
	public boolean delWordById(int fid) {
		String sql = "delete from exam_word where fid = ?";
		Connection connection = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		try {
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setInt(1, fid);
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
	public List<ExamWord> getAllWordsByEid(int eid) {
		String sql = "select * from exam_word where eid = ? order by fid";
		Connection connection = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		try {
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setInt(1, eid);
			ResultSet rs = ps.executeQuery();
			ExamWord file = null;
			List<ExamWord> result = new ArrayList<>();
			while (rs.next()) {
				file = new ExamWord();
				file.setFid(rs.getInt("fid"));
				file.setPron(rs.getString("pronunciation"));
				file.setPath(rs.getString("path"));
				file.setType(rs.getString("ftype"));
				file.setDesc(rs.getString("fdesc"));
				file.setEid(eid);
				file.setName(rs.getString("name"));
				result.add(file);
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
	public ExamWord getExamWordByVid(int fid) {
		String sql = "select * from exam_word where fid = ?";
		Connection connection = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		try {
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setInt(1, fid);
			ResultSet rs = ps.executeQuery();
			ExamWord file = null;
			while (rs.next()) {
				file = new ExamWord();
				file.setFid(rs.getInt("fid"));
				file.setPron(rs.getString("pronunciation"));
				file.setPath(rs.getString("path"));
				file.setType(rs.getString("ftype"));
				file.setDesc(rs.getString("fdesc"));
				file.setEid(rs.getInt("eid"));
				file.setName(rs.getString("name"));
			}
			return file;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JDBCUtil.close(connection, ps);
		}
	}

	@Override
	public ExamWord getExamWordByEidAndWid(int eid, int wid) {
		String sql = "select * from exam_word where eid = ? and fid = ?";
		Connection connection = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		try {
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setInt(1, eid);
			ps.setInt(2, wid);
			ResultSet rs = ps.executeQuery();
			ExamWord file = null;
			while (rs.next()) {
				file = new ExamWord();
				file.setFid(rs.getInt("fid"));
				String pron = rs.getString("pronunciation");
				file.setPron(null == pron ? "":pron);
				file.setPath(rs.getString("path"));
				file.setType(rs.getString("ftype"));
				file.setDesc(rs.getString("fdesc"));
				file.setEid(rs.getInt("eid"));
				file.setName(rs.getString("name"));
			}
			return file;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JDBCUtil.close(connection, ps);
		}
	}

}
