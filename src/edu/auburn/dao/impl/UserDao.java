package edu.auburn.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import edu.auburn.dao.IUserDao;
import edu.auburn.domain.EduUser;
import edu.auburn.utils.JDBCUtil;
import edu.auburn.utils.UserUtils;

public class UserDao implements IUserDao {
	@Override
	public boolean addVerifyCodeByEmail(String email, int code) {
		String sql = "update euser set vcode = ? where email = ?";
		Connection connection = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		try {
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setInt(1, code);
			ps.setString(2, email);
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
	public boolean delVerifyCodeByEmail(String email) {
		String sql = "update euser set vcode = ? where email = ?";
		Connection connection = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		try {
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setInt(1, 0);
			ps.setString(2, email);
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
	public boolean updatePassword(String mail, String confirm) {
		String sql = "update euser set password = ? where email = ?";
		Connection connection = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		try {
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setString(1, confirm);
			ps.setString(2, mail);
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
	public boolean checkVerifyCodeByEmail(String email, int code) {
		String sql = "select * from euser where email = ? and vcode = ?";
		Connection connection = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		try {
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setString(1, email);
			ps.setInt(2, code);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JDBCUtil.close(connection, ps);
		}
		return false;
	}
	@Override
	public boolean addUser(EduUser user) {
		// create table euser(
		// uid integer auto_increment primary key,
		// udate datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
		// name varchar(50),
		// password varchar(20),
		// email varchar(50),
		// utype integer
		// )engine = myisam default charset = utf8;
		String sql = "insert into euser (name, password, email, utype, udate) values (?,?,?,?,?)";
		Connection connection = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		try {
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setString(1, user.getName());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getEmail());
			ps.setInt(4, user.getType());
			ps.setDate(5, user.getDate());
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
	public boolean delUserById(int uid) {
		String sql = "delete from euser where uid = ?";
		Connection connection = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		try {
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setInt(1, uid);
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
	public boolean updateUserTypeById(int id, int type) {
		String sql = "update euser set utype = ? where uid = ?";
		Connection connection = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		try {
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setInt(1, type);
			ps.setInt(2, id);
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
	public List<EduUser> getAll() {
		String sql = "select * from euser order by uid";
		Connection connection = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		try {
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			EduUser u = null;
			List<EduUser> result = new ArrayList<>();
			while (rs.next()) {
				if (rs.getInt("utype") != 1) {
					u = new EduUser();
					u.setUid(rs.getInt("uid"));
					u.setName(rs.getString("name"));
					u.setEmail(rs.getString("email"));
					int t = rs.getInt("utype");
					u.setType(t);
					u.setDate(rs.getDate("udate"));
					u.setRole(UserUtils.getRoleByType(t));
					result.add(u);
				}
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
	public boolean checkUserByEmailAndPwd(String email, String pwd) {
		String sql = "select * from euser where email = ? and password = ?";
		Connection connection = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		try {
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, pwd);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JDBCUtil.close(connection, ps);
		}
		return false;
	}

	@Override
	public EduUser getUserByEmail(String email) {
		String sql = "select * from euser where email = ?";
		Connection connection = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		try {
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			EduUser u = null;
			if (rs.next()) {
				u = new EduUser();
				u.setUid(rs.getInt("uid"));
				u.setName(rs.getString("name"));
				u.setEmail(rs.getString("email"));
				u.setType(rs.getInt("utype"));
				u.setDate(rs.getDate("udate"));
				u.setRole(UserUtils.getRoleByType(rs.getInt("utype")));
			}
			return u;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JDBCUtil.close(connection, ps);
		}
	}

	@Override
	public boolean checkUserEmailExist(String email) {
		String sql = "select * from euser where email = ?";
		Connection connection = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		try {
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JDBCUtil.close(connection, ps);
		}
	}

	@Override
	public EduUser getUserByName(String name) {
		String sql = "select * from euser where name = ?";
		Connection connection = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		try {
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			EduUser u = null;
			if (rs.next()) {
				u = new EduUser();
				u.setUid(rs.getInt("uid"));
				u.setName(rs.getString("name"));
				u.setEmail(rs.getString("email"));
				u.setType(rs.getInt("utype"));
				u.setDate(rs.getDate("udate"));
				u.setRole(UserUtils.getRoleByType(rs.getInt("utype")));
			}
			return u;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JDBCUtil.close(connection, ps);
		}
	}

	@Override
	public EduUser getUserById(int uid) {
		String sql = "select * from euser where uid = ?";
		Connection connection = JDBCUtil.getConnection();
		PreparedStatement ps = null;
		try {
			ps = (PreparedStatement) connection.prepareStatement(sql);
			ps.setInt(1, uid);
			ResultSet rs = ps.executeQuery();
			EduUser u = null;
			if (rs.next()) {
				u = new EduUser();
				u.setUid(rs.getInt("uid"));
				u.setName(rs.getString("name"));
				u.setEmail(rs.getString("email"));
				u.setType(rs.getInt("utype"));
				u.setDate(rs.getDate("udate"));
				u.setRole(UserUtils.getRoleByType(rs.getInt("utype")));
			}
			return u;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			JDBCUtil.close(connection, ps);
		}
	}

}
