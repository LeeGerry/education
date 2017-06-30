package edu.auburn.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import edu.auburn.dao.ILessonFileDao;
import edu.auburn.domain.LessonFile;
import edu.auburn.utils.JDBCUtil;

public class LessonFileDao implements ILessonFileDao {
	// create table lesson_file(
	// fid integer auto_increment primary key,
	// name varchar(100),
	// path varchar(1000),
	// ftype varchar(10),
	// fdesc varchar(2000),
	// lid integer
	// )engine = myisam default charset = utf8;
	// select * from lesson_file;
	@Override
	public boolean addFile(LessonFile file) {
		String sql = "insert into lesson_file (name, path, ftype, fdesc, lid) values (?,?,?,?,?)";
		Connection connection = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		try {
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setString(1, file.getName());
			ps.setString(2, file.getPath());
			ps.setString(3, file.getFtype());
			ps.setString(4, file.getFdesc());
			ps.setInt(5, file.getLid());
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
	public boolean delFileById(int id) {
		String sql = "delete from lesson_file where fid = ?";
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
	public List<LessonFile> getAllFileByLid(int lid) {
		String sql = "select * from lesson_file where lid = ? order by fid";
		Connection connection = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		try {
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setInt(1, lid);
			ResultSet rs = ps.executeQuery();
			LessonFile file = null;
			List<LessonFile> result = new ArrayList<>();
			while (rs.next()) {
				file = new LessonFile();
				file.setFid(rs.getInt("fid"));
				file.setName(rs.getString("name"));
				file.setPath(rs.getString("path"));
				file.setFtype(rs.getString("ftype"));
				file.setFdesc(rs.getString("fdesc"));
				file.setLid(lid);
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
	public LessonFile getLessonFileByFid(int fid) {
		String sql = "select * from lesson_file where fid = ?";
		Connection connection = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		try {
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setInt(1, fid);
			ResultSet rs = ps.executeQuery();
			LessonFile file = null;
			while (rs.next()) {
				file = new LessonFile();
				file.setFid(rs.getInt("fid"));
				file.setName(rs.getString("name"));
				file.setPath(rs.getString("path"));
				file.setFtype(rs.getString("ftype"));
				file.setFdesc(rs.getString("fdesc"));
				file.setLid(rs.getInt("lid"));
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
