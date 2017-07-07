package edu.auburn.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import edu.auburn.dao.IExamVideoDao;
import edu.auburn.domain.ExamVideo;
import edu.auburn.utils.JDBCUtil;

public class ExamVideoDao implements IExamVideoDao {
//	create table exam_video(
//			fid integer auto_increment primary key,
//			name varchar(100),
//			path varchar(1000),
//			ftype varchar(10),
//			fdesc varchar(2000),
//			eid integer
//		)engine = myisam  default charset = utf8;
//

	@Override
	public boolean addVideo(ExamVideo video) {
		String sql = "insert into exam_video (name, path, ftype, fdesc, eid) values (?,?,?,?,?)";
		Connection connection = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		try {
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setString(1, video.getName());
			ps.setString(2, video.getPath());
			ps.setString(3, video.getType());
			ps.setString(4, video.getDesc());
			ps.setInt(5, video.getEid());
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
	public boolean delVideoById(int vid) {
		String sql = "delete from exam_video where fid = ?";
		Connection connection = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		try {
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setInt(1, vid);
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
	public List<ExamVideo> getAllVideosByEid(int eid) {
		String sql = "select * from exam_video where eid = ? order by fid";
		Connection connection = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		try {
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setInt(1, eid);
			ResultSet rs = ps.executeQuery();
			ExamVideo file = null;
			List<ExamVideo> result = new ArrayList<>();
			while (rs.next()) {
				file = new ExamVideo();
				file.setVid(rs.getInt("fid"));
				file.setName(rs.getString("name"));
				file.setPath(rs.getString("path"));
				file.setType(rs.getString("ftype"));
				file.setDesc(rs.getString("fdesc"));
				file.setEid(eid);
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
	public ExamVideo getExamVideoByVid(int vid) {
		String sql = "select * from exam_video where fid = ?";
		Connection connection = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		try {
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setInt(1, vid);
			ResultSet rs = ps.executeQuery();
			ExamVideo file = null;
			while (rs.next()) {
				file = new ExamVideo();
				file.setVid(rs.getInt("fid"));
				file.setName(rs.getString("name"));
				file.setPath(rs.getString("path"));
				file.setType(rs.getString("ftype"));
				file.setDesc(rs.getString("fdesc"));
				file.setEid(rs.getInt("eid"));
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
