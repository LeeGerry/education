package edu.auburn.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.StringUtils;

import edu.auburn.dao.ICommentDao;
import edu.auburn.dao.IUserDao;
import edu.auburn.domain.Comment;
import edu.auburn.domain.EduUser;
import edu.auburn.utils.JDBCUtil;

public class CommentDao implements ICommentDao {

	@Override
	public boolean addComment(Comment comment) {
		String sql = "insert into comment (uid, comment, commentdate) values (?,?,?)";
		Connection connection = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		try {
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setInt(1, comment.getUid());
			ps.setString(2, comment.getComment());
			ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
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
	public boolean delCommentById(int id) {
		String sql = "delete from comment where cid = ?";
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
	public List<Comment> getAll() {
		String sql = "select * from comment";
		Connection connection = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		try {
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			Comment c = null;
			List<Comment> result = new ArrayList<>();
			IUserDao udao = new UserDao();
			while (rs.next()) {
				c = new Comment();
				c.setCid(rs.getInt("cid"));
				c.setUid(rs.getInt("uid"));
				c.setComment(rs.getString("comment"));
				Timestamp ts = rs.getTimestamp("commentdate");
				c.setDate(ts.toLocaleString());
				int uid = rs.getInt("uid");
				EduUser u = udao.getUserById(uid);
				String uname = "";
				if (u != null && !StringUtils.isNullOrEmpty(u.getName())) {
					uname = u.getName();
				}
				c.setUname(uname);
				result.add(c);
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
	public Comment getCommentById(int cid) {
		String sql = "select * from comment where cid = ?";
		Connection connection = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		try {
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			Comment c = null;
			IUserDao udao = new UserDao();
			if (rs.next()) {
				c = new Comment();
				c.setCid(rs.getInt("cid"));
				c.setUid(rs.getInt("uid"));
				c.setComment(rs.getString("comment"));
				Timestamp ts = rs.getTimestamp("commentdate");
				c.setDate(ts.toLocaleString());
				int uid = rs.getInt("uid");
				EduUser u = udao.getUserById(uid);
				String uname = "";
				if (u != null && !StringUtils.isNullOrEmpty(u.getName())) {
					uname = u.getName();
				}
				c.setUname(uname);
			}
			return c;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JDBCUtil.close(connection, ps);
		}
	}
}
