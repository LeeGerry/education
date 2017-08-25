package edu.auburn.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import edu.auburn.dao.IExamVideoDao;
import edu.auburn.dao.IWordVideoDao;
import edu.auburn.domain.ExamVideo;
import edu.auburn.domain.WordVideo;
import edu.auburn.utils.JDBCUtil;

public class WordVideoDao implements IWordVideoDao {

//create table word_video(
//	fid integer auto_increment primary key,
//	wid integer,
//	eid integer,
//	name varchar(100),
//	path varchar(1000),
//	fdesc varchar(2000)
//)engine = myisam  default charset = utf8;

	@Override
	public boolean addVideo(WordVideo video) {
		String sql = "insert into word_video (wid, eid, name, path, fdesc) values (?,?,?,?,?)";
		Connection connection = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		try {
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setInt(1, video.getWid());
			ps.setInt(2, video.getEid());
			ps.setString(3, video.getName());
			ps.setString(4, video.getPath());
			ps.setString(5, video.getDesc());
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
		String sql = "delete from word_video where fid = ?";
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
	public List<WordVideo> getAllVideosByWid(int wid) {
		String sql = "select * from word_video where wid = ? order by fid";
		Connection connection = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		try {
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setInt(1, wid);
			ResultSet rs = ps.executeQuery();
			WordVideo file = null;
			List<WordVideo> result = new ArrayList<>();
			while (rs.next()) {
				file = new WordVideo();
				file.setVid(rs.getInt("fid"));
				file.setName(rs.getString("name"));
				file.setPath(rs.getString("path"));
				file.setDesc(rs.getString("fdesc"));
				file.setEid(rs.getInt("eid"));
				file.setWid(wid);
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
	public WordVideo getWordVideoByVid(int vid) {
		String sql = "select * from word_video where fid = ?";
		Connection connection = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		try {
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setInt(1, vid);
			ResultSet rs = ps.executeQuery();
			WordVideo file = null;
			while (rs.next()) {
				file = new WordVideo();
				file.setVid(rs.getInt("fid"));
				file.setName(rs.getString("name"));
				file.setPath(rs.getString("path"));
				file.setDesc(rs.getString("fdesc"));
				file.setEid(rs.getInt("eid"));
				file.setWid(rs.getInt("wid"));
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
